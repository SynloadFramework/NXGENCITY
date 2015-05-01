package us.nxgencity.map.sql;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import us.nxgencity.map.MapModule;
import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.NonSQL;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.FloatColumn;
import com.synload.framework.modules.annotations.sql.StringColumn;
import com.synload.framework.sql.Model;

@SQLTable(description = "Individual slide", name = "Entry", version = 0.3)
public class Entry extends Model {
	public Entry(ResultSet rs) {
		super(rs);
		this.setHtml(MapModule.bbcode.process(this.getBody()));
		try {
			img = this._related(IconImage.class).exec(IconImage.class);
			simg = this._related(SupportImage.class).exec(SupportImage.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | NoSuchFieldException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Entry(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10, Key = true, AutoIncrement = true)
	public long id;
	
	@StringColumn(length = 512)
	@HasMany(key = "id", of = IconImage.class)
	public String icons;
	
	@StringColumn(length = 512)
	@HasMany(key = "id", of = SupportImage.class)
	public String images;
	
	@StringColumn(length = 500)
	public String body;
	
	@StringColumn(length = 200)
	public String title;
	
	@FloatColumn()
	public int x;
	
	@FloatColumn()
	public int y;
	
	@StringColumn(length=50)
	public String presenter;
	
	@NonSQL
	public List<IconImage> img;
	
	@NonSQL
	public String html;
	
	@NonSQL
	public List<SupportImage> simg;

	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public List<IconImage> getImg() {
		return img;
	}
	public void setImg(List<IconImage> img) {
		this.img = img;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public List<SupportImage> getSimg() {
		return simg;
	}
	public void setSimg(List<SupportImage> simg) {
		this.simg = simg;
	}
	public String getPresenter() {
		return presenter;
	}
	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIcons() {
		return icons;
	}
	public void setIcons(String icons) {
		this.icons = icons;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
