package klktest.myweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="klk_major")
public class Major extends BaseEntity {

	private static final long serialVersionUID = 4478388900416946996L;

	@Id
	@GenericGenerator(name="redisInc", strategy="assigned")
	private long id;
	@Column
	private String name;
	@Column
	private String code;
	@Column
	private int score;
	@Column
	private String description;
	@Column
	private int dr=0;
	@Column
	private Date ts=new Date();
	@Column
	private Date createtime=new Date();
	
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Major [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	@Override
	public String getPrimaryKey() {
		return "id";
	}

	@Override
	public String getTableName() {
		return "klk_major";
	}

	@Override
	public void setPrimaryKey(long pk) {
		setId(pk);
	}

}
