package us.nxgencity.map.sql;

import java.sql.ResultSet;

import com.synload.framework.modules.annotations.HasOne;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.StringColumn;
import com.synload.framework.sql.Model;
import com.synload.framework.users.User;

@SQLTable(description = "Icon Image", name = "IconImage", version = 0.1)
public class IconImage extends Model {
	public IconImage(ResultSet rs) {
		super(rs);
	}
	public IconImage(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10, Key = true, AutoIncrement = true)
	public long id;
	
	@StringColumn(length = 255)
	public String url;
	
	@BigIntegerColumn(length = 20)
	@HasOne(key = "id", of = User.class)
	public long user;
	
	
	@BigIntegerColumn(length = 10)
	@HasOne(key = "id", of = Entry.class)
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
