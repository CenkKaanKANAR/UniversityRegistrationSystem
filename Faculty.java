
public class Faculty {
		
		private int ID;
		private String name;
		private String surname;
		private String departmentName;
		private AssignInfo[] assignInfoList;
		
		
		public Faculty() {
			
		}
		
		public Faculty(int ID, String name, String surname, String departmentName) {
			
			this.ID = ID;
			this.name = name;
			this.surname = surname;
			this.departmentName = departmentName;
		}
		
		
		
		
		public boolean withdrawAssignInfo(Course course) {
			
			AssignInfo[] tempAssingInfoList = null;
			int len = this.assignInfoList.length;
			boolean result = false;
			
			if( len == 1) {
				
				this.assignInfoList[0] = null;
				result = true;
		
			}
			else {
				
				int i = 0;
				
				while(i < len) {
					
					if(this.assignInfoList[i].getCourse().getName() == course.getName()) {
							
						len--;
						tempAssingInfoList = new AssignInfo[len];
						
						if(i == 0) {

							for ( int j = 0; j < len; j++) {
								tempAssingInfoList[j] = this.assignInfoList[j+1]; 
								}
							
						}
						else if(i == this.assignInfoList.length-1) {
							
							for ( int k = 0; k < len; k++ ) {
								tempAssingInfoList[k] = this.assignInfoList[k];
								}
							
						}
						else if(i != 0 && i != this.assignInfoList.length-1) {
							
							for ( int t = 0; t < i; t++ ) {
								tempAssingInfoList[t] = this.assignInfoList[t];
						
							}
							
							for ( int z = i+1; z < len+1; z++ ) {
								tempAssingInfoList[z-1] = this.assignInfoList[z];
						
							}
								
						}
							
						this.assignInfoList = new AssignInfo[len];
						
						for ( int n = 0; n < len; n++ ) {
							this.assignInfoList[n] = tempAssingInfoList[n];
						}
					
						result = true;
						
					}
	
					i++;
				}//while
					
			}//else
			
		return result;
		
		}
		
		
		public void addAssignInfo(AssignInfo assignInfo) {
			
			int len = 0;
			AssignInfo[] tempAssignInfoList = null;
			
			if(this.assignInfoList == null) {
				
				this.assignInfoList = new AssignInfo[1];
				this.assignInfoList[0] = assignInfo;
				
			}
			else {
				
				len = this.assignInfoList.length; 
				len++;
				tempAssignInfoList = new AssignInfo[len];
				
				for ( int i = 0; i < tempAssignInfoList.length; i++ ) {
					
					if ( i != tempAssignInfoList.length-1 ) 
						tempAssignInfoList[i] = this.assignInfoList[i];
					else
						tempAssignInfoList[i] = assignInfo;
					
				}
				
				this.assignInfoList = new AssignInfo[len];
				
				for ( int j = 0; j < this.assignInfoList.length; j++ ) {
					
					this.assignInfoList[j] = tempAssignInfoList[j];
					
				}
				
			}
			
			
		}
		
		
		
		public void printCourseList() {
			
			System.out.println("------------------------------------------------------------------------------------- ");
			System.out.println("printCourseList METHOD OF INSTRUCTOR ->");
			System.out.println("Instructor : " + this.name + " " + this.surname + " Department : " + this.departmentName);
			
			if( this.assignInfoList == null || this.assignInfoList[0] == null) {
				System.out.println("      No Course Assigned");
				System.out.println("------------------------------------------------------------------------------------- ");
			}
			else {
				
				for(int i = 0; i < this.assignInfoList.length; i++) {
					System.out.println("      Course : " + this.assignInfoList[i].getCourse().getName());
					System.out.println("------------------------------------------------------------------------------------- ");
				}
				
			}
		}
		
	
		
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		public AssignInfo[] getAssignInfoList() {
			return assignInfoList;
		}
		public void setAssingInfoList(AssignInfo[] assignInfoList) {
			this.assignInfoList = assignInfoList;
		}

	
	
}
