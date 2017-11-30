package klktest.myweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="klk_college")
public class College extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="redisInc", strategy="assigned")
	private long id;
	@Column
	private String name;
	@Column
	private String code;
	@Column
	private String address;
	@Column
	private Date buildon;
	@Column
	private int dr=0;
	@Column
	private Date ts=new Date();
	@Column
	private Date createtime = new Date();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getBuildon() {
		return buildon;
	}
	public void setBuildon(Date buildon) {
		this.buildon = buildon;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatetime() {
		return createtime;
	}
	@Override
	public String getPrimaryKey() {
		return "id";
	}
	@Override
	public String getTableName() {
		return "klk_college";
	}
	@Override
	public void setPrimaryKey(long pk) {
		this.setId(pk);
	}
	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
