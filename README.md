# atm-simulator-server

###Technology stack:<br/>
1. Jersey 2.24<br/>
2. Hibernate 4.3<br/>
3. Maven 3<br/>
4. Tomcat 8<br/>

###Run instructions:<br/>
1. Find DB scripts at: src\main\resources\dbscript_atm_db.sql. Import data in your database<br/>
2. Edit DB credentials in the file: src\main\resources\hibernate\hibernate.cfg.xml<br/>
3. Go to project dir, run command: mvn clean package<br/>
4. Pick up build from /target dir and deploy on your application server<br/>
5. Start the server<br>

###Valid ATM card details for demo:<br/>
CARD 1<br/>
Customer Name: Manish Meshram<br/>
Card Number: 1001<br/>
Account Number: 1001<br/>
PIN: 1234<br/>

CARD 2<br/>
Customer Name: Rahul Agarwal<br/>
Card Number: 1002<br/>
Account Number: 1002<br/>
PIN: 3456<br/>