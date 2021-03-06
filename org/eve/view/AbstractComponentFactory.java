package org.eve.view;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eve.main.EVE;
import org.eve.main.EveAPI;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public abstract class AbstractComponentFactory implements ComponentFactory {
    private ComboAssist comboassist;
    private Locale locale;
    private MessageSource messages;
    private EveAPI system;
    private Map<String, Component> fields;
    private DateFormat dateformat;
    
    public AbstractComponentFactory() {
        fields = new LinkedHashMap<String, Component>();
        comboassist = new ComboAssist();
    }

    /**
     * 
     * @param component
     */
    protected abstract void setControlFocus(Component component);
    
    /**
     * 
     * @param component
     * @param index
     */
    protected abstract void setControlFocus(Component component, int index);
    
    /**
     * 
     * @param component
     * @param value
     */
    protected abstract void setControlValue(Component component, Object value);
    
    /**
     * 
     * @param component
     * @param index
     * @param value
     */
    protected abstract void setControlValue(
            Component component, int index, String value);
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setControlSize(
     *     org.eve.view.Component)
     */
    @Override
    public abstract void setControlSize(Component component);
    
    /**
     * 
     * @param component
     * @return
     */
    protected abstract Object getControlValue(Component component);
    
    /**
     * 
     * @param component
     * @param index
     * @return
     */
    protected abstract Object getControlValue(Component component, int index);
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setDate(
     *     java.lang.String, java.util.Date)
     */
    @Override
    public final void setDate(String field, Date date) {
        Component component = fields.get(field);
        
        if (date == null)
            setControlValue(component, "");
        else
            setControlValue(component, dateformat.format(date));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setDate(
     *     java.lang.String, int, java.util.Date)
     */
    @Override
    public final void setDate(String field, int index, Date date) {
        Component component = fields.get(field);
        
        if (date == null)
            setControlValue(component, index, "");
        else
            setControlValue(component, index, dateformat.format(date));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFieldValue(
     *     java.lang.String, java.lang.Object)
     */
    @Override
    public final void setFieldValue(String id, Object value) {
        switch (fields.get(id).getDataType()) {
        case CHAR:
            setString(id, (String)value);
            break;
            
        case DATE:
            setDate(id, (Date)value);
            break;
            
        case TIME:
            setTime(id, (Time)value);
            break;
        
        case INT:
            setInt(id, (Integer)value);
            break;
        
        case LONG:
            setLong(id, (Long)value);
            break;
            
        case FLOAT:
            setFloat(id, (Float)value);
            break;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFieldValue(java.lang.String, int, java.lang.Object)
     */
    @Override
    public final void setFieldValue(String id, int index, Object value) {
        switch (fields.get(id).getDataType()) {
        case CHAR:
            setString(id, index, (String)value);
            break;
            
        case DATE:
            setDate(id, index, (Date)value);
            break;
            
        case TIME:
            setTime(id, index, (Time)value);
            break;
        
        case INT:
            setInt(id, index, (Integer)value);
            break;
        
        case LONG:
            setLong(id, index, (Long)value);
            break;
            
        case FLOAT:
            setFloat(id, index, (Float)value);
            break;
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFloat(java.lang.String, float)
     */
    @Override
    public final void setFloat(String field, float value) {
        Component component = fields.get(field);
        
        switch (component.getType()) {
        case COMBO:
            setControlValue(component, Float.toString(value));
            break;
            
        default:
            setControlValue(fields.get(field), Float.toString(value));
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFloat(java.lang.String, int, float)
     */
    @Override
    public final void setFloat(String field, int index, float value) {
        Component component = fields.get(field);
        
        switch (component.getType()) {
        case COMBO:
            setControlValue(component, index, Float.toString(value));
            break;
            
        default:
            setControlValue(fields.get(field), index, Float.toString(value));
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFocus(java.lang.String)
     */
    @Override
    public final void setFocus(String field) {
        setControlFocus(fields.get(field));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setInt(java.lang.String, int)
     */
    @Override
    public final void setInt(String field, int value) {
        Component component = fields.get(field);
        
        switch (component.getType()) {
        case COMBO:
            setControlValue(component, value);
        default:
            setControlValue(component, Integer.toString(value));
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setInt(java.lang.String, int, int)
     */
    @Override
    public final void setInt(String field, int index, int value) {
        setControlValue(fields.get(field), index, Integer.toString(value));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
        dateformat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setLong(java.lang.String, long)
     */
    @Override
    public final void setLong(String field, long value) {
        setControlValue(fields.get(field), Long.toString(value));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setLong(java.lang.String, int, long)
     */
    @Override
    public final void setLong(String field, int index, long value) {
        setControlValue(fields.get(field), index, Long.toString(value));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setMessage(int, java.lang.String)
     */
    @Override
    public final void setMessage(int status, String id) {
        system.setMessage(status, getMessage(id));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setMessages(org.springframework.context.MessageSource)
     */
    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setString(java.lang.String, java.lang.String)
     */
    @Override
    public final void setString(String field, String text) {
        Component component = fields.get(field);
        
        if (text == null)
            setControlValue(component, "");
        else
            setControlValue(component, text);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setString(java.lang.String, int, java.lang.String)
     */
    @Override
    public final void setString(String field, int index, String text) {
        Component component = fields.get(field);
        
        if (text == null)
            setControlValue(component, index, "");
        else
            setControlValue(component, index, text);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setSystem(org.eve.main.EveAPI)
     */
    @Override
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setTime(java.lang.String, java.sql.Time)
     */
    @Override
    public final void setTime(String field, Time time) {
        Component component = fields.get(field);
        
        if (time == null)
            setControlValue(component, "");
        else
            setControlValue(component, time.toString());
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setTime(java.lang.String, int, java.sql.Time)
     */
    @Override
    public final void setTime(String field, int index, Time time) {
        Component component = fields.get(field);
        
        if (time == null)
            setControlValue(component, index, "");
        else
            setControlValue(component, index, time.toString());
    }
    
    /*
     * 
     * Getters
     * 
     */

    /**
     * 
     * @return
     */
    protected final ComboAssist getComboAssist() {
        return comboassist;
    }
    
    /**
     * 
     * @param field
     * @return
     */
    protected final Component getComponent(String field) {
        return fields.get(field);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getComponents()
     */
    @Override
    public final Collection<Component> getComponents() {
        return fields.values();
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getDate(java.lang.String)
     */
    @Override
    public final Date getDate(String field) {
        try {
            return dateformat.parse(
                    (String)getControlValue(fields.get(field)));
        } catch (ParseException ex) {
            system.setMessage(EVE.error, getMessage("invalid.date.format"));
            setControlFocus(fields.get(field));
            ex.printStackTrace();
        }
        
        return null;
    }    

    @Override
    public final Date getDate(String field, int index) {
        try {
            return dateformat.parse((String)getControlValue(fields.get(field), index));
        } catch (ParseException ex) {
            system.setMessage(EVE.error, getMessage("invalid.date.format"));
            setControlFocus(fields.get(field), index);
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public final double getDouble(String field) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component);
        
        try {
            return Double.parseDouble((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.double.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    @Override
    public final double getDouble(String field, int index) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component, index);
        
        try {
            return Double.parseDouble((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.double.format"));
            setControlFocus(component, index);
            
            throw ex;
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFieldValue(org.eve.model.AbstractDocument, java.lang.String)
     */
    @Override
    public final Object getFieldValue(String id) {
        Component component = fields.get(id);
        
        /*
         * significa que o campo do objeto não existe no formulário
         */
        if (component == null)
            return null;
        
        switch (component.getDataType()) {
        case CHAR:
            return getString(id);
            
        case DATE:
            return getDate(id);

        case DOUBLE:
            return getDouble(id);
        
        case FLOAT:
            return getFloat(id);
        
        case INT:
            return getInt(id);
        
        case LONG:
            return getLong(id);
            
        case TIME:
            return getTime(id);
            
        default:
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFieldValue(java.lang.String, int)
     */
    @Override
    public final Object getFieldValue(String id, int index) {
        Component component = fields.get(id);
        
        /*
         * significa que o campo do objeto não existe no formulário
         */
        if (component == null)
            return null;
        
        switch (component.getDataType()) {
        case CHAR:
            return getString(id, index);
            
        case DATE:
            return getDate(id, index);
        
        case DOUBLE:
            return getDouble(id, index);
        
        case FLOAT:
            return getFloat(id, index);
        
        case INT:
            return getInt(id, index);
        
        case LONG:
            return getLong(id, index);
            
        case TIME:
            return getTime(id, index);
            
        default:
            return null;
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFloat(java.lang.String)
     */
    @Override
    public final float getFloat(String field) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component);
        
        try {
            return Float.parseFloat((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.float.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFloat(java.lang.String, int)
     */
    @Override
    public final float getFloat(String field, int index) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component, index);
        
        try {
            return Float.parseFloat((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.float.format"));
            setControlFocus(component, index);
            
            throw ex;
        }
    }
    
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getInt(java.lang.String)
     */
    @Override
    public final int getInt(String field) {
        String text;
        Component component = fields.get(field);
        
        try {
            switch (component.getType()) {
            case COMBO:
                return (Integer)getControlValue(component);
            
            default:
                text = (String)getControlValue(component);
                return Integer.parseInt((text.length() == 0)? "0":text);
            }
            
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getInt(java.lang.String, int)
     */
    @Override
    public final int getInt(String field, int index) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component, index);
        
        try {
            return Integer.parseInt((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /**
     * 
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getLong(java.lang.String)
     */
    @Override
    public final long getLong(String field) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component);
        
        try {
            return Long.parseLong((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    @Override
    public final long getLong(String field, int index) {
        Component component = fields.get(field);
        String text = (String)getControlValue(component, index);
        
        try {
            return Long.parseLong((text.length() == 0)? "0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component, index);
            
            throw ex;
        }
    }

    /**
     * 
     * @param message
     * @return
     */
    @Override
    public final String getMessage(String tag) {
        return messages.getMessage(tag, null, tag, locale);        
    }
    
    /**
     * 
     * @param name
     * @param length
     * @param key
     * @return
     */
    protected abstract Component getNewComponent(String name, int length, boolean key);
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getString(java.lang.String)
     */
    @Override
    public final String getString(String field) {
        return (String)getControlValue(fields.get(field));
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getString(java.lang.String, int)
     */
    @Override
    public final String getString(String field, int index) {
        return (String)getControlValue(fields.get(field), index);
    }
    
    /**
     * 
     * @return
     */
    protected final EveAPI getSystem() {
        return system;
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getTime(java.lang.String)
     */
    @Override
    public final Time getTime(String field) {
//        return fields.get(field).getTime();
        return null;
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getTime(java.lang.String, int)
     */
    @Override
    public final Time getTime(String field, int row) {
        String value = getString(field, row);
        int len;
            
        if (value.equals(""))
            return Time.valueOf("00:00:00");
        
        value = value.replace(":", "");
        len = value.length();
        
        if ((len % 2) != 0) {
            setMessage(EVE.error, getMessage("invalid.time.format"));
            setControlFocus(fields.get(field));
            
            throw new IllegalArgumentException();
        }
        
        if ((len != 4) && (len != 6)) {
            setMessage(EVE.error, getMessage("invalid.time.format"));
            setControlFocus(fields.get(field));
            
            throw new IllegalArgumentException();
        }
        
        if (len == 4)
            value = value.concat("00");
        
        value = new StringBuffer(value.substring(0, 2))
            .append(":").append(value.substring(2, 4))
            .append(":").append(value.substring(4, 6)).toString();
        
        setString(field, row, value);
        
        return Time.valueOf(value);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#isComboAssistCustomized()
     */
    @Override
    public final boolean isComboAssistCustomized() {
        return comboassist.isCustomized();
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#copyFrom(org.eve.model.AbstractDocument)
     */
    @Override
    public final void copyFrom(AbstractDocument document) {
        for (String id: fields.keySet())
            setFieldValue(id, document.getFieldValue(id));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#copyFrom(java.util.Set)
     */
    @Override
    public final void copyFrom(Set<AbstractDocument> itens) {
        int i = 0;

        for (String id : fields.keySet()) {
            for (AbstractDocument item : itens)
                setFieldValue(id, ++i, item.getFieldValue(id));
            i = 0;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#copyFrom(int, org.eve.view.ComponentFactory)
     */
    @Override
    public final void copyFrom(int index, ComponentFactory factory) {
        for (String id : fields.keySet())
            setFieldValue(id, index, factory.getFieldValue(id));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#put(
     *     org.eve.model.AbstractDocument, java.lang.String)
     */
    @Override
    public final void put(AbstractDocument document, String id) {
        Component component = getNewComponent(
                id, document.getLength(id), !document.isKey(id));
        
        component.setTitle(getMessage(id));
        component.setDataType(document.getType(id));
        
        putComponent(id, component);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#put(
     *     java.lang.String, org.eve.view.Component, boolean)
     */
    @Override
    public final void put(String id, Component component, boolean key) {
        Component component_ = getNewComponent(id, component.getLength(), key);
        
        component_.setTitle(getMessage(id));
        component_.setDataType(component.getDataType());
        
        putComponent(id, component_);
    }
    
    /**
     * 
     * @param document
     * @param id
     * @param length
     */
    public final void putCombo(AbstractDocument document, String id, int length) {
        Component component = getNewComponent(id, length, !document.isKey(id));
        
        component.setTitle(getMessage(id));
        component.setType(ComponentType.COMBO);
        component.setOptions(document.getValues(id));
        component.setDataType(document.getType(id));
        
        putComponent(id, component);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#putComponent(java.lang.String, org.eve.view.Component)
     */
    @Override
    public final void putComponent(String name, Component component) {
        fields.put(name, component);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#sel(int, int)
     */
    @Override
    public final void sel(int col, int row) {
//        Object[] objects = fields.values().toArray();
//        Control control = ((Component)objects[col]).getControl();
//        
//        if (control instanceof Text)
//            ((Text)control).selectAll();
//        
//        control.setFocus();
    }
    
}
