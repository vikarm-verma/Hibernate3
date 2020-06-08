import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.*;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  
      
public class StoreData {    
 public static void main(String[] args) {    
      
 StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
	  
	SessionFactory factory=meta.getSessionFactoryBuilder().build();  
	Session session=factory.openSession();  
   
Transaction t=session.beginTransaction();    
        
    ArrayList<String> list1=new ArrayList<String>();    
    list1.add("Java is a programming language");    
    list1.add("Java is a platform");    
        
    ArrayList<String> list2=new ArrayList<String>();    
    list2.add("Servlet is an Interface");    
    list2.add("Servlet is an API");    
        
    Question question1=new Question();    
    question1.setQname("What is Java?");    
    question1.setAnswers(list1);    
        
    Question question2=new Question();    
    question2.setQname("What is Servlet?");    
    question2.setAnswers(list2);    
        
    session.persist(question1);    
    session.persist(question2);    
        
    
    TypedQuery query=session.createQuery("select q from Question q",Question.class);    
    @SuppressWarnings("unchecked")
	List<Question> list=query.getResultList();   
     
    Iterator<Question> itr=list.iterator();    
    while(itr.hasNext()){    
    	System.out.println("it is in while");
        Question q=itr.next();    
        System.out.println("Question Name: "+q.getQname());    
            
        //printing answers    
        List<String> list3=q.getAnswers();    
        Iterator<String> itr2=list3.iterator();    
        while(itr2.hasNext()){    
            System.out.println(itr2.next());    
        }          
    }    
    
    t.commit();    
    session.close();    
    System.out.println("success");    
 }    
}    