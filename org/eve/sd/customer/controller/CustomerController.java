package org.eve.sd.customer.controller;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class CustomerController extends AbstractController {
    
    @Override
    public final void userInput(String input) {
        int k;
        String name;
        CustomerContact contact;
        CustomerAddress address;
        CustomerSchedule schedule;
        Customer customer = (Customer)getObject();
        Model model = getModel();
        Form form = getForm("main");
        TableAssist contacts = getTable("contacts");
        TableAssist addresses = getTable("addresses");
        TableAssist schedules = getTable("schedule");
        
        if (input.equals("customer.save")) {
            try {
                /*
                 * dados base
                 */
                customer.setAlternateName(form.getString("customer.aname"));
                customer.setCodCadNac(form.getString("customer.cnpj"));
                customer.setId(form.getInt("customer.ident"));
                customer.setName(form.getString("customer.name"));
                customer.setStatus(form.getInt("customer.status"));
                
                /*
                 * inclui contatos
                 */
                customer.getContacts().clear();
                for (k = 0; k < contacts.getItensSize(); k++) {
                    name = contacts.getStringValue("contact.rname", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    contact = new CustomerContact();
                    
                    contact.setCustomer(customer);
                    contact.setItem(k);
                    contact.setName(name);
                    contact.setFunction(contacts.getStringValue("contact.funct", k));
                    
                    customer.getContacts().add(contact);
                }
                
                /*
                 * inclui endereços
                 */
                customer.getAddresses().clear();
                for (k = 0; k < contacts.getItensSize(); k++) {
                    name = addresses.getStringValue("address.logra", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    address = new CustomerAddress();
                    
                    address.setCustomer(customer);
                    address.setItem(k);
                    address.setAddress(name);
                    address.setNumber(addresses.getIntValue("address.numer", k));
                    
                    customer.getAddresses().add(address);
                }
                
                /*
                 * inclui horários
                 */
                customer.getSchedule().clear();
                for (k = 0; k < schedules.getItensSize(); k++) {                    
                    schedule = new CustomerSchedule();
                    
                    schedule.setCustomer(customer);
                    schedule.setItem(k);
                    schedule.setType(schedules.getIntValue("schedule.typ", k));                    
                    schedule.setMonday(schedules.getTimeValue("schedule.mon", k));
                    schedule.setTuesday(schedules.getTimeValue("schedule.tue", k));
                    schedule.setWednesday(schedules.getTimeValue("schedule.wed", k));
                    schedule.setThursday(schedules.getTimeValue("schedule.thu", k));
                    schedule.setFriday(schedules.getTimeValue("schedule.fri", k));
                    schedule.setSaturday(schedules.getTimeValue("schedule.sat", k));
                    schedule.setSunday(schedules.getTimeValue("schedule.sun", k));
                    
                    customer.getSchedule().add(schedule);
                }
                
                model.save(customer);
                form.setInt("customer.ident", customer.getId());                
                setMessage(EVE.status, "customer.save.success");
                
                return;
            } catch (Exception ev) {                
                setMessage(EVE.error, "customer.save.error");
                ev.printStackTrace();
            }
        }
        
        if (input.equals("contacts.new")) {
            System.out.println("inserir");
        }
        
        if (input.equals("contacts.del")) {
            System.out.println("remover");
        }
    }
}
