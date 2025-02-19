package kz.bitlab.javaee.database;

import kz.bitlab.javaee.models.Category;
import kz.bitlab.javaee.models.News;
import kz.bitlab.javaee.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {
    private static Connection connection;

    static{

        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(

                    "jdbc:postgresql://localhost:5432/javaee-final",
                    "postgres",
                    "postgres"

            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void addUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(email, password, first_name, last_name, role_id) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getRole_id());

            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserByEmail(String email){
        User user = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole_id(resultSet.getString("role_id"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public static void addCategory(Category category){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO news_categories(name) VALUES (?)");
            preparedStatement.setString(1, category.getName());
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_categories");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    public static Category getCategoryById(long id){
        Category category = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_categories WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    public static void deleteCategory(long id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM news_categories WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateCategory(Category category){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_categories SET name = ? WHERE id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addNews(News news){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO news(title,content,category_id,post_date) VALUES (?,?,?,?)");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setLong(3, news.getCategory().getId());
            preparedStatement.setTimestamp(4, news.getPostDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                News news1 = new News();
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));
                news1.setId(resultSet.getLong("id"));
                news1.setPostDate(resultSet.getTimestamp("post_date"));

                Category category = getCategoryById(resultSet.getLong("category_id"));
                news1.setCategory(category);
                news.add(news1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(long id){
        News news = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                news = new News();
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                Category category = getCategoryById(resultSet.getLong("category_id"));
                if(category != null){
                    news.setCategory(category);
                }
                news.setPostDate(resultSet.getTimestamp("post_date"));
                news.setId(resultSet.getLong("id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return news;
    }
    public static void updateNews(News news){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news SET title = ?,content = ?,category_id = ?,post_date = ? WHERE id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setLong(3, news.getCategory().getId());
            preparedStatement.setTimestamp(4, news.getPostDate());
            preparedStatement.setLong(5, news.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteNews(long id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM news WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole_id(resultSet.getString("role_id"));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(long id){
        User user = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole_id(resultSet.getString("role_id"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getLong("id"));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ?, role_id = ?  WHERE id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getRole_id());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(Long id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
