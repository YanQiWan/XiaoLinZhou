package processmanager;

import javax.swing.JOptionPane;

import filemanager.Document;

public class ReadEvent extends FileEvent {

	Document doc;

	public ReadEvent(Document doc) {
		this.doc = doc;

		this.size = ProcessEvent.READSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		String content;
		if((content=fr.read(p, doc))!=null)
		{
			JOptionPane.showMessageDialog(null, "读取成功，内容为："+content);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "读取失败！");
		}
	}

}
