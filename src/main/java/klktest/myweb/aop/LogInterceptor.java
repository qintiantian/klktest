package klktest.myweb.aop;

import java.lang.reflect.Method;

import klktest.myweb.anonation.OprationLog;
import klktest.myweb.entity.BaseEntity;
import klktest.myweb.entity.OprLog;
import klktest.myweb.enums.OperationResult;
import klktest.myweb.enums.OperationType;
import klktest.myweb.pub.BaseEntityIdSetter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Aspect
@Order(10)
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class LogInterceptor {
	@Autowired
	private HibernateTemplate template;
	@Autowired
	private BaseEntityIdSetter idSetter;

	@Pointcut(value = "execution(public * klktest.myweb.pub.PublicServiceImpl.*(..))")
	public void logPointcut() {
	};

	@Before("logPointcut()")
	public void recordLog(JoinPoint joinpoint) {
		if(isRecord(joinpoint)){
			OprLog logVO = genLog(joinpoint, OperationResult.SUCCESS);
			logVO.setMessage("调用方法"+joinpoint.getSignature().getName());
			template.save(logVO);
		}
	}

	@AfterThrowing(value = "logPointcut()", throwing = "ex")
	public void recordExceptionLog(JoinPoint joinpoint, Throwable ex) {
		if(isRecord(joinpoint)) {
			OprLog logVO = genLog(joinpoint, OperationResult.FAILED);
			logVO.setMessage("调用方法"+joinpoint.getSignature().getName()+"失败："+ex.getMessage());
			template.save(logVO);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isRecord(JoinPoint joinpoint) {
		Signature signature = joinpoint.getSignature();
		Class clazz = signature.getDeclaringType();
		OprationLog clazzAno = (OprationLog) clazz
				.getAnnotation(OprationLog.class);
		String methodName = signature.getName();
		Object[] params = joinpoint.getArgs();
		Class[] parameterTypes = new Class[params.length];
		for(int i=0; i<params.length; i++) {
			if(i == params.length-1 && params[i] instanceof BaseEntity[]){
				parameterTypes[i] = BaseEntity[].class;
				break;
			}
			parameterTypes[i] = params[i].getClass().getSuperclass();
		}
		try {
			Method method = clazz.getMethod(methodName, parameterTypes);
			OprationLog methodAno = method.getAnnotation(OprationLog.class);
			if(methodAno == null){
				if(clazzAno != null)
					return clazzAno.isRecord();
				else
					return false;
			}
			return methodAno.isRecord();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

	private OprLog genLog(JoinPoint joinpoint, OperationResult result) {
		Signature signature = joinpoint.getSignature();
		String qualifiedName = signature.getDeclaringTypeName();
		OprLog logVO = new OprLog();
		logVO.setOprClass(qualifiedName+" "+signature.getName());
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
