package klktest.myweb.aop;

import klktest.myweb.anonation.OprationLog;
import klktest.myweb.entity.OprLog;
import klktest.myweb.enums.OperationResult;
import klktest.myweb.enums.OperationType;
import klktest.myweb.pub.BaseEntityIdSetter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Aspect
@Transactional
public class LogInterceptor {
	@Autowired
	private HibernateTemplate template;
	@Autowired
	private BaseEntityIdSetter idSetter;

	@Pointcut(value = "execution(public * klktest.myweb.pub.PublicServiceImpl.*(..))")
	public void logPointcut() {
	};

	@After("logPointcut()")
	public void recordLog(JoinPoint joinpoint) {
		if(isRecord(joinpoint)){
			OprLog logVO = genLog(joinpoint, OperationResult.SUCCESS);
			logVO.setMessage("执行成功");
			template.save(logVO);
		}
	}

	@AfterThrowing(value = "logPointcut()", throwing = "ex")
	public void recordExceptionLog(JoinPoint joinpoint, Throwable ex) {
		if(isRecord(joinpoint)) {
			OprLog logVO = genLog(joinpoint, OperationResult.FAILED);
			logVO.setMessage(ex.getMessage().substring(0, 500));
			template.save(logVO);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isRecord(JoinPoint joinpoint) {
		Signature signature = joinpoint.getSignature();
		Class clazz = signature.getDeclaringType();
		OprationLog logAno = (OprationLog) clazz
				.getAnnotation(OprationLog.class);
		if (logAno == null) {
			String methodName = signature.getName();
			return false;
		}
		return logAno.isRecord();
	}

	private OprLog genLog(JoinPoint joinpoint, OperationResult result) {
		Signature signature = joinpoint.getSignature();
		String qualifiedName = signature.getDeclaringTypeName();
		OprLog logVO = new OprLog();
		logVO.setOprClass(qualifiedName);
		logVO.setOprResult(result.getName());
		OperationType oprType = getOprType(signature.getName());
		logVO.setOprType(oprType.getName());
		idSetter.setId(logVO);
		return logVO;
	}

	private OperationType getOprType(String name) {
		if(name.startsWith("save")) {
			return OperationType.ADD;
		}else if(name.startsWith("insert")){
			return OperationType.BATCH_ADD;
		}else if(name.startsWith("update")){
			return OperationType.UPDATE;
		}else if(name.startsWith("delete")){
			return OperationType.DELETE;
		}else if(name.startsWith("query")){
			return OperationType.QUERY;
		}
		return OperationType.DEFAULT;
	}
}
