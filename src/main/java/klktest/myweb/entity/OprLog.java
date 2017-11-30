package klktest.myweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="klk_oprlog")
@Entity
public class OprLog extends BaseEntity {

	private static final long serialVersionUID = -9053044577435168059L;
	
	@Id
	@GenericGenerator(name="redisInc", strategy="assigned")
	private long id;
	
	@Column(name="oprclass")
	private String oprClass;
	
	@Column(name="oprtype")
	private String oprType;
	
	@Column(name="oprresult")
	private String oprResult;
	
	@Column(name="message")
	private String message;
	
	@Column
	private int dr=0;
	
	@Column
	private Date ts=new Date();
	
	@Column
	private Date createtime=new Date();

	@Override
	public String getPrimaryKey() {
		return "id";
	}

	@Override
	public String getTableName() {
		return "klk_oprlog";
	}

	@Override
	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOprClass() {
		return oprClass;
	}

	public void setOprClass(String oprClass) {
		this.oprClass = oprClass;
	}

	public String getOprType() {
		return oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}

	public String getOprResult() {
		return oprResult;
	}

	public void setOprResult(String oprResult) {
		this.oprResult = oprResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public Date getCreatetime() {
		return createtime;
	}

	@Override
	public String toString() {
		return "OprLog [id=" + id + ", oprClass=" + oprClass + ", oprType="
				+ oprType + ", oprResult=" + oprResult + ", message=" + message
				+ ", ts=" + ts + "]";
	}

}
