package backend;
 
class QuizResult { 
private String id; 
private String username; 
private int score; 
public QuizResult(String id, String username, int score) { 
this.id = id; 
this.username = username; 
this.score = score; 
} 
public String getId() { 
return id; 
} 
public void setId(String id) { 
this.id = id; 
} 
public String getUsername() { 
return username; 
} 
public void setUsername(String username) { 
this.username = username; 
} 
public int getScore() { 
return score; 
} 
public void setScore(int score) { 
this.score = score; 
} 
}