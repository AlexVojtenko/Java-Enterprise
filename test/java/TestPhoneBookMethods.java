import entity.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestPhoneBookMethods {

    private PhoneBook phoneBook;

    @Before
    public void createPhoneBookMethod() {
        phoneBook = new PhoneBook();
    }

    @Test
    public void testGetAllMethod() {
        ArrayList<Contact> contacts = (ArrayList<Contact>) phoneBook.getAll();
        Assert.assertNotNull(contacts);
    }

    @Test
    public void testSaveRecordMethod() {
        int beforeRunMethodContactsNumber = phoneBook.getAll().size();
        phoneBook.saveRecord(new Contact(0, null, null, 0, null));
        int afterRunMethodContactsNumber = phoneBook.getAll().size();
        phoneBook.deleteContact(0);
        Assert.assertTrue(afterRunMethodContactsNumber > beforeRunMethodContactsNumber);
    }

    @Test
    public void testDeleteContactMethod() {
        phoneBook.saveRecord(new Contact(0, null, null, 0, null));
        int beforeRunMethodContactsNumber = phoneBook.getAll().size();
        phoneBook.deleteContact(0);
        int afterRunMethodContactsNumber = phoneBook.getAll().size();
        Assert.assertTrue(afterRunMethodContactsNumber < beforeRunMethodContactsNumber);
    }

    @Test
    public void testUpdateContact() {
        ArrayList<Contact> contacts = (ArrayList<Contact>) phoneBook.getAll();
        int before = contacts.get(0).getAge();
        phoneBook.updateContact(contacts.get(0), contacts.get(0).getNumber(), contacts.get(0).getFullName(),
                contacts.get(0).getAge() + 1, contacts.get(0).getInfo());
        ArrayList<Contact> contacts2 = (ArrayList<Contact>) phoneBook.getAll();
        int after = contacts2.get(0).getAge();
        phoneBook.updateContact(contacts.get(0), contacts.get(0).getNumber(), contacts.get(0).getFullName(),
                before, contacts.get(0).getInfo());
        Assert.assertNotEquals(before, after);
    }
}
