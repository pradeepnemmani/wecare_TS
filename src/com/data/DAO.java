package com.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.naming.java.javaURLContextFactory;
import com.model.Comment;
import com.model.Event;
import com.model.Topic;
import com.model.User;
import com.mysql.jdbc.Statement;

public class DAO {
	public static void saveUser(User user)throws Exception{
		Connection con=null;
		try {
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("insert user values(?,?,?,?,?,?)");
			st.setString(1,user.getUserid());
			st.setString(2, user.getPassword());
			st.setString(3, user.getName());
			st.setString(4, user.getGender());
			st.setString(5, user.getCity());
			st.setString(6,"");
			int result=st.executeUpdate();
			if(result!=1){
				throw new Exception("Data not inserted");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public static User getUser(String uid)throws Exception
	{
		Connection con=null;
		User usr=null;
		
		try{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("select * from user where userid=?");
			st.setString(1,uid);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				usr=new User();
				usr.setUserid(rs.getString(1));						
				usr.setPassword(rs.getString(2));
				usr.setName(rs.getString(3));
				usr.setGender(rs.getString(4));
				usr.setCity(rs.getString(5));
				String imageURL=rs.getString(6);
				if(imageURL.length()!=0)
				{
					usr.setImage(rs.getString(6));
				
				}
				else {
					usr.setImage(null);
					
				}
			
				
			}
			else{
				throw new Exception("Invalid userid");
			}
				
			} catch (Exception e1) {
				throw new Exception(e1.getMessage());
			}
		return usr;
	}
	public static boolean saveTopic(Topic topic)throws Exception{
		Connection con=null;
		boolean status=false;
	try {
		con=DBManager.getConnection();
		PreparedStatement st=con.prepareStatement("insert into topic (tname,comment,userid,time)values(?,?,?,?)");	
		st.setString(1,topic.getTname());
		st.setString(2,topic.getComment());
		st.setString(3,topic.getUserid());
//		st.setTimestamp(4, new java.sql.Timestamp(topic.getDate().getTime()));
		st.setString(4,topic.getDate());
		
		int result=st.executeUpdate();
		status=true;
		if(result!=1){
			throw new Exception("Data not inserted");
		}
	} catch (Exception e2) {
		throw new Exception(e2.getMessage());
	}
	return status;

}
	public static List<Topic> getTopics(String userId)throws Exception
	{
		Connection con=null;
		List<Topic> list=new ArrayList<>();
		try
		{
		con=DBManager.getConnection();
		
		PreparedStatement st=con.prepareStatement("select * from topic WHERE topic.userid=?");
		st.setString(1, userId);
		ResultSet set=st.executeQuery();
		while(set.next())
		{
			Topic topic=new Topic();
//			topic.setTid(set.getString(1));
			topic.setTid(set.getInt(1));
			topic.setTname(set.getString(2));
			topic.setComment(set.getString(3).trim());
			topic.setUserid(set.getString(4));
			topic.setDate(set.getString(5));
			list.add( topic);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		return list;
	}
	public static boolean deleteTopic(int topicId)throws Exception
	{
		boolean status=false;
		Connection con=null;
		try{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("DELETE from topic WHERE tid=?");
			st.setInt(1, topicId);
			int result=st.executeUpdate();
			status=true;
			if(result==1)
			{
				try {
					deleteComments(topicId);
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}			
			}
			else{
				throw new Exception("Unable to Delete the Topic, Due to technical problems. Please try later...!");
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return status;
	}
	public static Topic getTopic(int topicId)throws Exception
	{
		Topic topic=new Topic();
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT * from topic WHERE tid=?");
			st.setInt(1, topicId);
			ResultSet set=st.executeQuery();
			while(set.next())
			{
				topic.setTid(set.getInt(1));
				topic.setTname(set.getString(2));
				topic.setComment(set.getString(3));
				topic.setUserid(set.getString(4));
				topic.setDate(set.getString(5));		
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return topic;
	}
	
	public static List<Topic> getAlltopics()throws Exception
	{
		List<Topic> list=new ArrayList<>();
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("SELECT * from topic");
			ResultSet set=st.executeQuery();
			while(set.next())
			{
				Topic topic=new Topic();
				topic.setTid(set.getInt(1));
				topic.setTname(set.getString(2));
				topic.setComment(set.getString(3));
				topic.setUserid(set.getString(4));
				topic.setDate(set.getString(5));	
				list.add(topic);
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return list;
	}
	public static boolean editTopic(Topic topic)throws Exception
	{
		boolean status=false;
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("UPDATE topic SET  tname=?, comment=? WHERE tid=?");
			st.setString(1, topic.getTname());
			st.setString(2, topic.getComment());
			st.setInt(3, topic.getTid());
			int result=st.executeUpdate();
			status=true;
			if(result!=1){
				throw new Exception("Data not updated");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		return status;
	}
	
	public static List<Topic> searchTopicByName(String topicName)throws Exception
	{
		List<Topic> topics=new ArrayList<>();
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement statement=con.prepareStatement("SELECT * from topic WHERE tname LIKE ?");
			statement.setString(1, "%" + topicName + "%");
			ResultSet set=statement.executeQuery();
			while(set.next())
			{
				Topic topic=new Topic();
				topic.setTid(set.getInt(1));
				topic.setTname(set.getString(2));
				topic.setComment(set.getString(3));
				topic.setUserid(set.getString(4));
				topic.setDate(set.getString(5));	
				topics.add(topic);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		return topics;
	}
	
	public static List<Comment> getComment(int topicId)throws Exception
	{
		List<Comment> comments=new ArrayList<>();
		Connection con=null;
	try
	{
		con=DBManager.getConnection();
		PreparedStatement st=con.prepareStatement("SELECT * FROM comment WHERE topicid=?");
		st.setInt(1, topicId);
		ResultSet set=st.executeQuery();
		while(set.next())
		{
			Comment comment=new Comment();
			comment.setTopicId(set.getInt(1));
			comment.setUserId(set.getString(2));
			comment.setComment(set.getString(3));
			comment.setTime(set.getString(4));
			comments.add(comment);
		}
	}
	catch (Exception e) {
		// TODO: handle exception
		throw new Exception(e.getMessage());
	}
		return comments;
	}
	
	public static boolean addComment(Comment comment)throws Exception
	{
		boolean status=false;
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement statement=con.prepareStatement("insert into comment(topicid,userid,comment,time) values(?,?,?,?)");
			statement.setInt(1, comment.getTopicId());
			statement.setString(2, comment.getUserId());
			statement.setString(3, comment.getComment());
			statement.setString(4, comment.getTime());
			int result=statement.executeUpdate();			
			if(result!=1){
				status=false;			
				}
			else {
				status=true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		 return  status;
	}
	
	public static boolean updateImage(String imageUrl,String userId)throws Exception
	{
		boolean status=false;
		Connection con=null;
		 try{
			 con=DBManager.getConnection();
			 PreparedStatement st=con.prepareStatement("update user SET image=? WHERE user.userid=?");
			 st.setString(1, imageUrl);
			 st.setString(2, userId);
			 int result=st.executeUpdate();
			 status=true;
				if(result!=1){
					throw new Exception("Data not updated");
				}
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return status;
	}
	
	public static void deleteComments(int topicId)throws Exception
	{
				Connection con=null;
		try{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("DELETE from comment WHERE topicid=?");
			st.setInt(1, topicId);
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void addEvent(Event event)throws Exception
	{
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("INSERT into event(name,description,date,plotno,area,city,state,country,pincode) values(?,?,?,?,?,?,?,?,?)");
			st.setString(1, event.getName());
			st.setString(2, event.getDescription());
			st.setString(3, event.getDate());
			st.setString(4, event.getPlotNo());
			st.setString(5, event.getArea());
			st.setString(6, event.getCity());
			st.setString(7, event.getState());
			st.setString(8, event.getCountry());
			st.setInt(9, event.getPincode());
			int result=st.executeUpdate();
			if(result!=1){
				throw new Exception("Event not inserted");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	public static List<Event> getEvents()throws Exception
	{
		List<Event> events=new ArrayList<>();
		Connection con=null;
		 try
		 {
			 con=DBManager.getConnection();
			 PreparedStatement st=con.prepareStatement("select * from event");
			 ResultSet set=st.executeQuery();
			 while(set.next())
			 {
				 Event event=new Event();
				 event.seteId(set.getInt(1));
				 event.setName(set.getString(2));
				 event.setDescription(set.getString(3));
				 event.setDate(set.getString(4));
				 event.setPlotNo(set.getString(5));
				 event.setArea(set.getString(6));
				 event.setCity(set.getString(7));
				 event.setState(set.getString(8));
				 event.setCounry(set.getString(9));
				 event.setPincode(set.getInt(10));
				 events.add(event);
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return events;
	}
	
	public static Event getEvent(int eventId)throws Exception
	{
		Event event=null;
		Connection con=null;
		 try
		 {
			 con=DBManager.getConnection();
			 PreparedStatement st=con.prepareStatement("select * from event WHERE eid=?");
			 st.setInt(1, eventId);
			 ResultSet set=st.executeQuery();
			 while(set.next())
			 {
				 event=new Event();
				 event.seteId(set.getInt(1));
				 event.setName(set.getString(2));
				 event.setDescription(set.getString(3));
				 event.setDate(set.getString(4));
				 event.setPlotNo(set.getString(5));
				 event.setArea(set.getString(6));
				 event.setCity(set.getString(7));
				 event.setState(set.getString(8));
				 event.setCounry(set.getString(9));
				 event.setPincode(set.getInt(10));
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return event;
	}
	public static void deleteEvent(int eventId)throws Exception
	{
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("DELETE from event WHERE eid=?");
			st.setInt(1, eventId);
			int result=st.executeUpdate();
			if(result!=1)
			{
				throw new Exception("Event Not Deleted..!");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	public static void editEdit(Event event)throws Exception
	{
		Connection con=null;
		try
		{
			con=DBManager.getConnection();
			PreparedStatement st=con.prepareStatement("Update event SET name=?,description=?,date=?,plotno=?,area=?,city=?,state=?,country=?,pincode=? WHERE eid=?");
			st.setInt(10,event.geteId());
			st.setString(1, event.getName());
			st.setString(2, event.getDescription());
			st.setString(3, event.getDate());
			st.setString(4, event.getPlotNo());
			st.setString(5, event.getArea());
			st.setString(6, event.getCity());
			st.setString(7, event.getState());
			st.setString(8, event.getCountry());
			st.setInt(9, event.getPincode());
			int result=st.executeUpdate();
			if(result!=1)
			{
				throw new Exception("Unable to Update The edit...!");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
}
				
				
				
			
		
			
		
		
		
	
		
			
			
	
	


