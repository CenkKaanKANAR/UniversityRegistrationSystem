
public class Student {
		
		private String name;
		private String surname;
		private String major;
		private int ID;
		private int year;
		private RegisterInfo[] registerList;
		
		public Student() {
			
		}

		public Student(String name, String surname, String major, int ID, int year) {
			
			this.name = name;
			this.surname = surname;
			this.major = major;
			this.ID = ID;
			this.year = year;
		}
		
		public void addRegisterInfo(RegisterInfo registerInfo) {
			
			RegisterInfo[] tempRegisterList;
			int len = 0;
			
			if(this.registerList == null) {
				
				this.registerList = new RegisterInfo[1];
				this.registerList[0] = registerInfo;
				
			}
			else {
				
				len = this.registerList.length;
				len++;
				tempRegisterList = new RegisterInfo[len];
				
				for(int i = 0; i < tempRegisterList.length; i++) {
					
					if ( i != tempRegisterList.length-1 ) 
						tempRegisterList[i] = this.registerList[i];
					else
						tempRegisterList[i] = registerInfo;
				}
				
				this.registerList = new RegisterInfo[len];
				
				for(int j = 0; j < this.registerList.length; j++) {
					
					this.registerList[j] = tempRegisterList[j];
				}
			
			
			}//else
		

		}//method
	
		
		public boolean removeRegisterInfo(Course course) {
			
			int i = 0;
			int len = this.registerList.length;
			RegisterInfo[] tempRegisterList;
			Course courseRemoved = null;
			
			boolean removalOperation = false;
			
			if(this.registerList != null) {
			
			  while(i < len) {
				
				if(this.registerList[i].getCourse() != null && this.registerList[i].getCourse().getName() == course.getName()) { 
					
					courseRemoved = this.registerList[i].getCourse();
					len--;
					
					tempRegisterList = new RegisterInfo[len];
					
					if(i == 0) {
						
						for ( int j = 0; j < len; j++) {
							tempRegisterList[j] = this.registerList[j+1]; 
						}
						
					}
					else if( i == this.registerList.length-1 ) {
						
						for ( int k = 0; k < len; k++ ) {
							tempRegisterList[k] = this.registerList[k];
						}
					
					}
					else if( i != 0 && i != this.registerList.length-1 ) {
						
						for ( int t = 0; t < i; t++ ) {
							tempRegisterList[t] = this.registerList[t];
					
						}
						
						for ( int z = i+1; z < len+1; z++ ) {
							tempRegisterList[z-1] = this.registerList[z];
					
						}
					
					}
					
					this.registerList = new RegisterInfo[len];
					
					for ( int n = 0; n < len; n++ ) {
						this.registerList[n] = tempRegisterList[n];
					}
					
					removalOperation = true;
					
				}//If
				else {
					System.out.println("Removal failed, _Course object of RegisterInfo object is null_ !!");
					removalOperation = false;
				}		
			  
			  }//While
			
			}//If
			else {
				System.out.println("RegisterList object array is null !!");
				removalOperation = false;
			}
		
		
		
		return removalOperation;
		
		
		}//method
		
		
		
		public void printCourseList() {
			
			System.out.println("------------------------------------------------------------------------------------- ");
			System.out.println("printCourseList METHOD OF STUDENT ->");
			System.out.println("STUDENT : " + this.name + " " + this.surname + " MAJOR : " + this.major);
			
			int i = 0;
			int len = 0;
			int totalCredits = 0;
			
			if(this.registerList != null) {
				
				len = this.registerList.length;
				
				while(i < len) {
				System.out.println("       COURSE : " + this.registerList[i].getCourse().getName() + " STATUS : " + this.registerList[i].getRegisterMessage());
				totalCredits += this.registerList[i].getCourse().getCredits();
				i++;
				}
			
				System.out.println("       TOTAL CREDITS : " + totalCredits);
			}
			else {
				
				System.out.println("       No Course Registered " );
				System.out.println("       TOTAL CREDITS : " + totalCredits);
			
			}
			System.out.println("----------------------------------------------------------------------------------------------");
		}

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getMajor() {
			return major;
		}

		public void setMajor(String major) {
			this.major = major;
		}

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public RegisterInfo[] getRegisterList() {
			return registerList;
		}

		public void setRegisterList(RegisterInfo[] registerList) {
			this.registerList = registerList;
		}
	

}
