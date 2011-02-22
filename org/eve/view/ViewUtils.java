package org.eve.view;

import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Utilitários para visão
 * @author francisco.prates
 *
 */
public class ViewUtils {
    
    /**
     * Retorna largura do texto
     * @param text
     * @return
     */
    public static final int getCharWidth(Drawable text) {
        GC gc = new GC(text);
        int charw = gc.getFontMetrics().getAverageCharWidth();
        
        gc.dispose();
        
        return charw;
    }
    
    /**
     * Retorna altura do texto
     * @param text
     * @return
     */
    public static final int getCharHeight(Drawable text) {
        GC gc = new GC(text);
        int charh = gc.getFontMetrics().getHeight();
        
        gc.dispose();
        
        return charh;
    }

    public static final Object getControlValue(
            Component component, Control control) {
        switch (component.getType()) {
        case COMBO:
            return component.getOptions().get(((Combo)control).getText());
            
        case TEXT:
            return ((Text)control).getText();
            
        default:
            return null;
        }
    }
    
    public static final void setControlSize(Component component, Control control) {
        int charw = getCharWidth(control);
        int charh = getCharHeight(control);
        
        control.setSize(control.computeSize(component.getLength() * charw, charh));
    }
    
    public static final void setControlText(Component component,
            Control control, Object value, ComponentFactory factory) {
        Map<String, ?> options = component.getOptions();
        
        switch (component.getType()) {
        case CCOMBO:
            for (String key : options.keySet()) {
                if (!options.get(key).equals(value))
                    continue;
                
                ((CCombo)control).setText(factory.getMessage(key));
                break;
            }
            
            break;
            
        case COMBO:
            for (String key : options.keySet()) {
                if (!options.get(key).equals(value))
                    continue;
                
                ((Combo)control).setText(factory.getMessage(key));
                break;
            }
            
            break;
            
        case TEXT:
            ((Text)control).setText((String)value);
            break;
        }
    }
    
    public static final void setEnabledControl(Control control, boolean enabled) {
        control.setEnabled(enabled);
    }
}
