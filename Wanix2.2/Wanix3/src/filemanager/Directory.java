package filemanager;

import java.util.HashMap;
import java.util.Map;

public class Directory extends File {
	public Map<String, File> files = new HashMap<String, File>();

}
