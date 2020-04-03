import util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsManagerApplication {

    static Path filepath = Paths.get("data", "contacts.txt");

    public static void addContact(List<Contact> contacts, Contact newContact) throws IOException {
        contacts.add(newContact);
        Files.write(filepath, ioOut(contacts).getBytes());
    }

    private static List<Contact> readContacts() {
        try {
            List<String> tempContactList = Files.readAllLines(filepath);
            List<Contact> contactsList = new ArrayList<>();
            for (int i = 1; i < tempContactList.size(); i++) {
                String[] thisContact = tempContactList.get(i).split(",");
                Contact contact = new Contact(thisContact[0], thisContact[1]);
                contactsList.add(contact);
            }
            return contactsList;
        } catch (IOException ioe) {
            System.out.println("Could not read file.");
            return null;
        }
    }

    private static String ioOut(List<Contact> contacts) {
        StringBuilder output = new StringBuilder("Name:,Phone:\n");
        for (Contact contact : contacts) {
            output.append(contact.getName()).append(",");
            output.append(contact.getNumber()).append("\n");
        }
        return output.toString();
    }

    private static int removeContact(List<Contact> contacts, String target) {

        int counter = 0;
        int index = -1;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(target)) {
                index = counter;
            }
            counter++;
        }
        return index;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("\nContacts I/O - created by Bryce and Jeff.\n");


        Input input = new Input();
        boolean confirm = true;
        List<Contact> contacts;
        String target;
        contacts = readContacts();
        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        do {
            System.out.println("Please select an option:\n");
            System.out.println("\t1. View contacts");
            System.out.println("\t2. Add a new contact");
            System.out.println("\t3. Search a contact by name");
            System.out.println("\t4. Delete an existing contact");
            System.out.println("\t5. Exit");

            int selection = input.getInt(1, 5, "\nPlease make your selection: ");

            switch (selection) {
                case 1:
                    if (contacts.size() == 0) {
                        System.out.println("\nYou have no contacts...Please add a new contact.");
                    } else {
                        System.out.println("\nHere are your contacts:\n");
                        int i = 0;
                        System.out.printf("\t%s  %-18s | %-15s |\n", "#","Name: ", "Phone #:");
                        System.out.println("\t-----------------------------------------");
                        for (Contact contact : contacts) {
                            i++;
                            System.out.printf("\t%d) %-18s | %-15s |\n", i, contact.getName(), contact.getNumber());
                        }
                    }
                    confirm = input.yesNo("\nwould you like to continue?");
                    break;
                case 2:
                    Contact newContact = new Contact(input.getString("Contact Name: "), input.getString("Contact Number: "));
                    addContact(contacts, newContact);
                    confirm = input.yesNo("would you like to continue?");
                    break;
                case 3:
                    target = input.getString("What is the contact's name?: ");
                    for (Contact contact : contacts) {
                        if (contact.getName().equalsIgnoreCase(target)) {
                            System.out.println("Name: " + target);
                            System.out.println("Phone#: " + contact.getNumber());
                        }
                    }
                    confirm = input.yesNo("would you like to continue?");
                    break;
                case 4:
                    target = input.getString("What is the contact's name?: ");
                    int indexToRemove= removeContact(contacts,target);
                    if (indexToRemove == -1){
                        System.out.println("That person was not found.");
                    }else{
                        confirm = input.yesNo("!!!  ARE YOU SURE YOU WANT TO DELETE THIS CONTACT?  !!!");
                        if (confirm){
                        contacts.remove(indexToRemove);
                            System.out.printf("\nContact: %s was removed from your list.\n");
                        }
                    }

                    confirm = input.yesNo("would you like to continue?");
                    break;
                case 5:
                    confirm = false;
                    break;
            }

        } while (confirm);

        System.out.println("\nGoodbye, and have a nice day!");
//        System.out.println(ioOut(contacts));
    }
}
