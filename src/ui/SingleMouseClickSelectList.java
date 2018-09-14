package ui;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListModel;

public class SingleMouseClickSelectList<E> extends JList<E> {
    
	public SingleMouseClickSelectList(ListModel<E> model) {
        super(model);
    }
    
    @Override 
    public void updateUI() {
        setForeground(null);
        setBackground(null);
        setSelectionForeground(null);
        setSelectionBackground(null);
        super.updateUI();
    }
    
    @Override 
    public void processMouseMotionEvent(MouseEvent e) {
        super.processMouseMotionEvent(convertMouseEvent(e));
    }
    
    @Override 
    public void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_ENTERED || e.getID() == MouseEvent.MOUSE_EXITED) {
            super.processMouseEvent(e);
        } else {
            if (getCellBounds(0, getModel().getSize() - 1).contains(e.getPoint())) {
                super.processMouseEvent(convertMouseEvent(e));
            } else {
                e.consume();
                requestFocusInWindow();
            }
        }
    }
    
    private MouseEvent convertMouseEvent(MouseEvent e) {
        return new MouseEvent(
            e.getComponent(),
            e.getID(), e.getWhen(),
            e.getModifiers() | Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),
            e.getX(), e.getY(),
            e.getXOnScreen(), e.getYOnScreen(),
            e.getClickCount(),
            e.isPopupTrigger(),
            e.getButton());
    }
}
