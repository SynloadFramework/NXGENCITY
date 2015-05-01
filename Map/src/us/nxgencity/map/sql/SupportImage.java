package us.nxgencity.map.sql;

import java.sql.ResultSet;

import com.synload.framework.modules.annotations.HasOne;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.StringColumn;
import com.synload.framework.sql.Model;

@SQLTable(description = "Support Image", name = "SupportImages", version = 0.1)
public class SupportImage extends Model {
	public SupportImage(ResultSet rs) {
		super(rs);
	}
	public SupportImage(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10, Key = true, AutoIncrement = true)
	public long id;
	
	@StringColumn(length = 255)
	public String url;
	
	@BigIntegerColumn(length = 2)
	public long user;
	
	@HasOne(key = "id", of = Entry.class)
	@BigIntegerColumn(length = 10)
	public long entry;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public long getEntry() {
		return entry;
	}
	public void setEntry(long entry) {
		this.entry = entry;
	}
	
	
	
}
