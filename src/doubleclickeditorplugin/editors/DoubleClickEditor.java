package doubleclickeditorplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class DoubleClickEditor extends TextEditor {

	public DoubleClickEditor() {
		super();
		setSourceViewerConfiguration(new DoubleClickConfiguration());
	}

}
