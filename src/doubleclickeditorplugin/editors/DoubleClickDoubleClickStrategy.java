package doubleclickeditorplugin.editors;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.*;
import org.eclipse.swt.internal.C;
import org.eclipse.ui.internal.part.Part;

public class DoubleClickDoubleClickStrategy implements ITextDoubleClickStrategy {
	protected ITextViewer fText;

	public void doubleClicked(ITextViewer part) {
		int pos = part.getSelectedRange().x;

		if (pos < 0)
			return;

		fText = part;

		/*
		if (!selectComment(pos)) {
			selectWord(pos);
		}
		*/
		TextSelection selection = selectLine(pos);
		MessageDialog.openInformation(
			part.getTextWidget().getShell(),
			"選択された文章",
			selection.getText()
		);
	}
	
	protected TextSelection selectLine(int caretPos) {
		TextSelection selection = null;

		IDocument doc = fText.getDocument();
		int startPos, endPos;

		try {

			int pos = caretPos;
			char c;

			while (pos >= 0) {
				c = doc.getChar(pos);
				if (c == Character.LINE_SEPARATOR)
				// if (!Character.isJavaIdentifierPart(c))
					break;
				--pos;
			}

			startPos = pos;

			pos = caretPos;
			int length = doc.getLength();

			while (pos < length) {
				c = doc.getChar(pos);
				if (c == Character.LINE_SEPARATOR)
				// if (!Character.isJavaIdentifierPart(c))
					break;
				++pos;
			}

			endPos = pos;
			selection = selectRange(doc, startPos, endPos);

		} catch (BadLocationException x) {
		}

		return selection;
	}

	private TextSelection selectRange(IDocument doc, int startPos, int stopPos) {
		int offset = startPos + 1;
		int length = stopPos - offset;
		fText.setSelectedRange(offset, length);
		
		return new TextSelection(doc, offset, length);
	}
}