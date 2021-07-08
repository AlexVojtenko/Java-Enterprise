import database.Database;
import entity.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private static final String SELECT_ALL_CONTACT_QUERY =
            "SELECT p.id, p.number, c.fullname, c.age, c.info FROM phonenumber p INNER JOIN contactinfo c ON c.numberid = p.id ";
    private static final String SAVE_PHONE_NUMBER_QUERY = "INSERT INTO phonenumber (id, number) VALUES (?,?); ";
    private static final String SAVE_CONTACT_INFO_QUERY = "INSERT INTO contactinfo (numberid,fullname,age,info) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PHONE_NUMBER_QUERY = "UPDATE phonenumber SET number = ? WHERE id = ?";
    private static final String UPDATE_CONTACT_INFO_QUERY = "UPDATE contactinfo SET fullName = ?, age = ?, info = ? WHERE numberid = ?";
    private static final String DELETE_PHONE_NUMBER_QUERY = "DELETE FROM phonenumber WHERE id = ?";
    private static final String DELETE_CONTACT_INFO_QUERY = "DELETE FROM contactinfo WHERE numberid = ?";


    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CONTACT_QUERY);
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setNumber(resultSet.getString("number"));
                contact.setFullName(resultSet.getString("fullname"));
                contact.setAge(resultSet.getInt("age"));
                contact.setInfo(resultSet.getString("info"));
                contacts.add(contact);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void saveRecord(Contact contact) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PHONE_NUMBER_QUERY);
            preparedStatement.setLong(1, contact.getId());
            preparedStatement.setString(2, contact.getNumber());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(SAVE_CONTACT_INFO_QUERY);
            preparedStatement.setLong(1, contact.getId());
            preparedStatement.setString(2, contact.getFullName());
            preparedStatement.setInt(3, contact.getAge());
            preparedStatement.setString(4, contact.getInfo());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact, String number, String fullName, int age, String info) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHONE_NUMBER_QUERY);
            preparedStatement.setString(1, number);
            preparedStatement.setLong(2, contact.getId());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(UPDATE_CONTACT_INFO_QUERY);
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, info);
            preparedStatement.setLong(4, contact.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(long id) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PHONE_NUMBER_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(DELETE_CONTACT_INFO_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
