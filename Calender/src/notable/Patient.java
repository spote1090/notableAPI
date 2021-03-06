package notable;

import java.util.Date;

public class Patient {
	//This example uses a Map to store data in memory, but this could just as easily use a database connection. The point is that this class provides access to our data. It uses a simple Patient class:
		
			private String name;
			private Date date;
			private String kind;
			
			public Patient(String name, Date date, String kind) {
				this.name = name;
				this.date = date;
				this.kind = kind;
			}

			public String getName() {
				return name;
			}

			public Date getDate() {
				return date;
			}

			public String getKind() {
				return kind;
			}
}
