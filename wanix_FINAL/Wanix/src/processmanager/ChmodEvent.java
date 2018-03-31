package processmanager;

import javax.swing.JOptionPane;

import filemanager.Disk;
import filemanager.Document;
import filemanager.File;

public class ChmodEvent extends FileEvent {

	File file;
	Group g;

	public ChmodEvent(File file, Group g) {
		this.file = file;
		this.g = g;
		this.size = ProcessEvent.CHMODSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		Object[] obj = { "OK" };
		if (!fr.chmod(p, file, g))
			JOptionPane.showOptionDialog(null, "ÐÞ¸ÄÈ¨ÏÞÊ§°Ü£¡", "´íÎó", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, obj, obj[0]);
	}

}
