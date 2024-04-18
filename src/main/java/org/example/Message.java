package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.Main.connectToDB;

public class Message {
    int x;
    String text;

    public Message(int x, String text) {
        this.x = x;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "x=" + x +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return x == message.x && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, text);
    }

    public static void example99() throws SQLException {
        Connection conn = connectToDB();
        ArrayList<Message> messages = loadMessage(conn);
        System.out.println("получен список из " + messages.size() + " сообщений");
        messages.forEach(System.out::println);


        conn.close();
    }
    public static ArrayList<Message> loadMessage(Connection conn) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        Statement st = conn.createStatement();
        String s = "SELECT * FROM public.\"test4\"";
        ResultSet rs = st.executeQuery("SELECT * FROM public.message");
        String text;
        while (rs.next()) {
            int x = rs.getInt("id");
            text = rs.getString("text");
//            double m = rs.getDouble("money");
            messages.add(new Message(x, text));
            System.out.println(text);

        }
        rs.close();
        st.close();
        return messages;
    }
//    public static void newMes(User user, Connection conn) throws SQLException {
//        PreparedStatement st = conn.prepareStatement("INSERT INTO public.\"test4\"\n" +
//                "(\"text\")\n" +
//                "VALUES(?);");
//        st.setString(1, user.fio);
//        st.setDouble(2, user.money);
//        int countInserted = st.executeUpdate();
//        System.out.println(countInserted + " строк добавлено");
//        st.close();
//    UPDATE public.message
//    SET "text"='', sender_id=0, target_id=0
//    WHERE id=0;
//    }
}