
public class Course {

		private String department;
		private String name;
		private int credits;
		private int prereqYear;
		private int maxEnrollment;
		private int reservedSeats;
		private Student[] studentList;
		private Student[] replacementList;
		private Faculty instructor;

		
		public Course() {
			
		}
		
		
		public Course(String department, String name, int credits, int prereqYear, int maxEnrollment,
				int reservedSeats) {
			
			this.department = department;
			this.name = name;
			this.credits = credits;
			this.prereqYear = prereqYear;
			this.maxEnrollment = maxEnrollment;
			this.reservedSeats = reservedSeats;
		}


		public RegisterInfo registerCourse(Student std) {
			
			
			double rate = 0;	
			
			int stdListLen = 0;
			Student tempStudentList[];
			RegisterInfo regInfo = new RegisterInfo();
			
			System.out.println("registerCourse METHOD -> COURSE : " + this.name + " STUDENT : " + std.getName() + " " + std.getSurname());
			
			if( std.getYear() >= this.prereqYear && std.getMajor() == this.department ) {
				
				if(this.studentList == null) {
					
					this.studentList = new Student[1];
					this.studentList[0] = std;
					
					regInfo.setRegisterID(1);
					regInfo.setCourse(this);
					regInfo.setResult("APPROVED");
					regInfo.setRegisterMessage("REQUEST APPROVED");
					std.addRegisterInfo(regInfo);
					
				}
				else if( this.studentList.length < this.maxEnrollment - this.reservedSeats ) {
					
					stdListLen = this.studentList.length;
					System.out.println(stdListLen);
					stdListLen++;
					tempStudentList = new Student[stdListLen];
					
					for ( int i = 0; i < tempStudentList.length; i++ ) {
						
						if ( i != tempStudentList.length-1 ) 
							tempStudentList[i] = this.studentList[i];
						else
							tempStudentList[i] = std;
						
							regInfo.setRegisterID(i);
						
					}
					
					this.studentList = new Student[stdListLen];
					
					for ( int j = 0; j < this.studentList.length; j++ ) {
						
						this.studentList[j] = tempStudentList[j];
						
					}	
					
					regInfo.setCourse(this);
					regInfo.setResult("APPROVED");
					regInfo.setRegisterMessage("REQUEST APPROVED");
					std.addRegisterInfo(regInfo);
					
				}
			//
				else if( std.getYear() >= this.prereqYear && this.studentList.length >= this.maxEnrollment - this.reservedSeats ) {
				
					int replacementListLen = 0;
					Student tempReplacementList[];
				
					if(this.reservedSeats == 0) {
						
						regInfo.setRegisterID(-1);
						regInfo.setCourse(this);
						regInfo.setResult("REJECTED");
						regInfo.setRegisterMessage("REQUEST REJECTED – QUATO");
					}
					
					else if( this.replacementList == null ) {
					
						this.replacementList = new Student[1];
						this.replacementList[0] = std;
					
						regInfo.setCourse(this);
						regInfo.setResult("WAITING");
						regInfo.setRegisterMessage("REQUEST WAITING - REPLACEMENT LIST");
						std.addRegisterInfo(regInfo);
					
					}
		
					else {
					
						replacementListLen = this.replacementList.length;
						replacementListLen++;
						tempReplacementList = new Student[replacementListLen];
					
						for ( int i = 0; i < tempReplacementList.length; i++ ) {
						
							if ( i != tempReplacementList.length-1 ) 
								tempReplacementList[i] = this.replacementList[i];
							else
								tempReplacementList[i] = std;
						
						}
					
						this.replacementList = new Student[replacementListLen];
					
						for ( int j = 0; j < this.replacementList.length; j++ ) {
						
							this.replacementList[j] = tempReplacementList[j];
						
						}
						regInfo.setCourse(this);
						regInfo.setResult("WAITING");
						regInfo.setRegisterMessage("REQUEST WAITING - REPLACEMENT LIST");
						std.addRegisterInfo(regInfo);
					}
				
				}//
							
				return regInfo;
			}
			else if(std.getYear() >= this.prereqYear && std.getMajor() != this.department) {
				
				if(this.studentList == null) {
					
					this.studentList = new Student[1];
					this.studentList[0] = std;
					
					regInfo.setRegisterID(1);
					regInfo.setCourse(this);
					regInfo.setResult("APPROVED");
					regInfo.setRegisterMessage("REQUEST APPROVED");
					std.addRegisterInfo(regInfo);
				}
				else if( this.studentList.length < this.maxEnrollment - this.reservedSeats) {
					
					stdListLen = this.studentList.length;
					System.out.println("dfsadasd");
					rate = this.studentList.length / this.maxEnrollment;
					
					if(rate < 0.7) {
						
						stdListLen++;
						tempStudentList = new Student[stdListLen];
					
						for ( int i = 0; i < tempStudentList.length; i++ ) {
						
							if ( i != tempStudentList.length-1 ) 
								tempStudentList[i] = this.studentList[i];
							else
								tempStudentList[i] = std;
							
							regInfo.setRegisterID(i);
						
						}
					
						this.studentList = new Student[stdListLen];
					
						for ( int j = 0; j < this.studentList.length; j++ ) {
						
							this.studentList[j] = tempStudentList[j];
						
						}	
						
						regInfo.setCourse(this);
						regInfo.setResult("APPROVED");
						regInfo.setRegisterMessage("REQUEST APPROVED");
						std.addRegisterInfo(regInfo);
					}
					else {
						regInfo.setRegisterID(-1);
						regInfo.setCourse(this);
						regInfo.setResult("REJECTED");
						regInfo.setRegisterMessage("REQUEST REJECTED – QUATO");
						
					}
				}
				
				return regInfo;	
			}//
			
			else {
				
				regInfo.setCourse(this);
				regInfo.setResult("REJECTED");
				regInfo.setRegisterMessage("REQUEST REJECTED – PREREQUISITE");
				
			}
		
			return regInfo;
		}


		public AssignInfo assignInstructor(Faculty instructor, boolean force) {
			
			int i = 0;
			AssignInfo assignInfo = null;
			
			
			if ( this.department == instructor.getDepartmentName() ) {
				
				if( force == true || (force == false && this.instructor == null) ) {
					
					if(this.instructor != null) {
						this.instructor.withdrawAssignInfo(this);
					}
					
					this.instructor = instructor;
					//assignInfoList = this.instructor.getAssignInfoList();
					assignInfo = new AssignInfo();
					assignInfo.setAssignResult("APPROVED");
					assignInfo.setAssignMessage("INSTRUCTOR " + instructor.getName() + " " + instructor.getSurname() + " ASSIGNED");
					assignInfo.setCourse(this);
					instructor.addAssignInfo(assignInfo);
	
				}
				else {
					
					assignInfo = new AssignInfo();
					assignInfo.setAssignResult("REJECTED");
					assignInfo.setAssignMessage( "ANOTHER INSTRUCTOR HAS ALREADY ASSIGNED");
					assignInfo.setCourse(this);
					instructor.addAssignInfo(assignInfo);
	
				}
			
			}
			else {
				
				assignInfo = new AssignInfo();
				assignInfo.setAssignResult("REJECTED");
				assignInfo.setAssignMessage( "DEPARTMENT MISMATCH");
				assignInfo.setCourse(this);
				instructor.addAssignInfo(assignInfo);

			}
			return assignInfo;
			
		}
		

		public void registerReplacementList() {
			
			System.out.println("RegisterReplacementList METHOD ->");
			
			Student[] tempStudentList = null;
			Student[] tempReplacementList = null;
			Student removedStudent = null;
			RegisterInfo[] regList = null;
			int len = 0;
			
			
			if( this.instructor != null) {
				
				if( this.replacementList != null && this.studentList.length < this.maxEnrollment ) {
					
					len = this.replacementList.length;
					
					for( int i = 0; i < this.replacementList.length; i++) {
						
						
						if( this.department == this.replacementList[i].getMajor() ) {
							
							len--;
							removedStudent = this.replacementList[i];
							
							tempReplacementList = new Student[len];
							
							if(i == 0 && len == 0) {
								 this.replacementList = null;
							}
							else if(i == 0 && len != 0) {
								
								for ( int j = 0; j < len; j++) {
									tempReplacementList[j] = this.replacementList[j+1]; 
								}
							
							}
							else if( i == this.replacementList.length-1) {
								
								for ( int k = 0; k < len; k++ ) {
									tempReplacementList[k] = this.replacementList[k];
								}
							
							}
							else if ( i != 0 && i != this.replacementList.length-1 ) {
								
								for ( int t = 0; t < i; t++ ) {
									tempReplacementList[t] = this.replacementList[t];
							
								}
								
								for ( int z = i+1; z < len+1; z++ ) {
									tempReplacementList[z-1] = this.replacementList[z];
							
								}
							}
							
							this.replacementList = new Student[len];
							
							for ( int n = 0; n < len; n++ ) {
								this.replacementList[n] = tempReplacementList[n];
							}
							
						
						len = this.studentList.length;
						len++;				
						
						tempStudentList = new Student[len];
						
						for ( int t = 0; t < tempStudentList.length; t++ ) {
							
							if ( t != tempStudentList.length-1 ) 
								tempStudentList[t] = this.studentList[t];
							else
								tempStudentList[t] = removedStudent;
							
						}
						
						this.studentList = new Student[len];
						
						for ( int j = 0; j < this.studentList.length; j++ ) {
							
							this.studentList[j] = tempStudentList[j];
							
						}
						
						regList = removedStudent.getRegisterList();
						
						
						for(int a = 0; a < regList.length; a++) {
							
							if( this.name == regList[a].getCourse().getName()) {
								
								System.out.println("Student : " + removedStudent.getName() + " assigned to " + this.name + " from replacement list." +
								 " (MajorControl = true) RegisterID : " + regList[a].getRegisterID() );
								
								regList[a].setResult("APPROVED");
								regList[a].setRegisterMessage("REQUEST APPROVED");
								
							}
							
						}
						
						
						}
						
					}
					
					
					len = this.replacementList.length;
					
					for( int k = 0; k < len; k++ ) {
						
						if( this.department != this.replacementList[k].getMajor() ) {
							
							removedStudent = this.replacementList[k];
							len--;
							
							tempReplacementList = new Student[len];
							
							if(k == 0 && len == 0) {
								 this.replacementList = null;
							}
							else if(k == 0) {
								
								for ( int j = 0; j < len; j++) {
									tempReplacementList[j] = this.replacementList[j+1]; 
								}
							
							}
							else if( k == this.replacementList.length-1) {
								
								for ( int l = 0; k < len; l++ ) {
									tempReplacementList[l] = this.replacementList[l];
								}
							
							}
							else if ( k != 0 && k != this.replacementList.length-1 ) {
								
								for ( int t = 0; t < k; t++ ) {
									tempReplacementList[t] = this.replacementList[t];
							
								}
								
								for ( int z = k+1; z < len+1; z++ ) {
									tempReplacementList[z-1] = this.replacementList[z];
							
								}
							}
							
						
							this.replacementList = new Student[len];
							
							for ( int n = 0; n < len; n++ ) {
								this.replacementList[n] = tempReplacementList[n];
							}
							
							regList = removedStudent.getRegisterList();
							
							for(int a = 0; a < regList.length; a++) {
								
								if( this.name == regList[a].getCourse().getName()) {
									
									System.out.println("Student : " + removedStudent.getName() + " assigned to " + this.name + " from replacement list." +
									 " (MajorControl = false) RegisterID : " + regList[a].getRegisterID() );
									
									regList[a].setResult("APPROVED");
									regList[a].setRegisterMessage("REQUEST APPROVED");
								}
								
							}
						
						}		
						
					}
									
				}
				System.out.println("NO STUDENT IN REPLACEMENT LIST");
				
			}
			else {
				System.out.println("INSTRUCTOR NOT ASSIGNED TO COURSE");
				System.out.println("-------------------------------------------------------------------------------------");
			}
			
		}
			
			
		
		public void printStudentList() {
			
			System.out.println("printStudentList METHOD OF COURSE->");
			System.out.println("COURSE : " + this.name + " DEPARTMENT : " + this.department );
			System.out.println("Registered Student List ");
			
			for( int i = 0; i < this.studentList.length; i++) {
				
				System.out.println("       Student ID : " + this.studentList[i].getID() + " Name : " + this.studentList[i].getName() + " " + this.studentList[i].getSurname() );
				
			}
			if(this.replacementList == null) {
				System.out.println("No Student in Replacement List ");
			}
			
		}
		


		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
		public int getPrereqYear() {
			return prereqYear;
		}
		public void setPrereqYear(int prereqYear) {
			this.prereqYear = prereqYear;
		}
		public int getMaxEnrollment() {
			return maxEnrollment;
		}
		public void setMaxEnrollment(int maxEnrollment) {
			this.maxEnrollment = maxEnrollment;
		}
		public int getReservedSeats() {
			return reservedSeats;
		}
		public void setReservedSeats(int reservedSeats) {
			this.reservedSeats = reservedSeats;
		}
		public Student[] getStudentList() {
			return studentList;
		}
		public void setStudentList(Student[] studentList) {
			this.studentList = studentList;
		}
		public Student[] getReplacementList() {
			return replacementList;
		}
		public void setReplacementList(Student[] replacementList) {
			this.replacementList = replacementList;
		}
		public Faculty getInstructor() {
			return instructor;
		}
		public void setInstructor(Faculty instructor) {
			this.instructor = instructor;
		}
		
		
	
	
	
}
