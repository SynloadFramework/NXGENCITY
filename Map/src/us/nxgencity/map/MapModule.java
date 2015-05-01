package us.nxgencity.map;

import java.io.File;

import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

import com.synload.framework.modules.ModuleClass;
import com.synload.framework.modules.annotations.Module;

@Module(author="Nathaniel Davidson", name="NexGenCityMaps", version="0.1")
public class MapModule extends ModuleClass{
	public static TextProcessor bbcode = BBProcessorFactory.getInstance().create(new File("bbcodes.xml"));
	@Override
	public void crossTalk(Object... arg0) {
		
	}

	@Override
	public void initialize() {
		
	}

}
