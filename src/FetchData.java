import javax.persistence.TypedQuery;  
import java.util.*;  
import org.hibernate.*;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  
    
public class FetchData {    
/**
 * @param args
 */
public static void main(String[] args) {    
        
    StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
    Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
      
    SessionFactory factory=meta.getSessionFactoryBuilder().build();  
    Session session=factory.openSession();  
    Transaction t=session.beginTransaction();  
    //hql 
    @SuppressWarnings("rawtypes")
    TypedQuery query=session.createQuery("select q from Question q",Question.class);    
    @SuppressWarnings("unchecked")
	List<Question> list=query.getResultList();   
     
    Iterator<Question> itr=list.iterator();    
    while(itr.hasNext()){    
    	System.out.println("it is in while");
        Question q=itr.next();    
        System.out.println("Question Name: "+q.getQname());    
            
        //printing answers    
        List<String> list2=q.getAnswers();    
        Iterator<String> itr2=list2.iterator();    
        while(itr2.hasNext()){    
            System.out.println(itr2.next());    
        }          
    }    
    t.commit();
    session.close();    
    System.out.println("success in fetch data");         
}    
}    