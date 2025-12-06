# ai-study-notes-java
Simple Java console-based GenAI project using Gemini API
# AI Study Notes Generator (Java Console App)

This is a simple **GenAI-based Java console application** that generates:
- Short summary
- 5 key points
- 5 interview questions with answers

for any topic entered by the user, using **Google Gemini API**.

---

## 🚀 Features

- Takes topic input from the user
- Generates easy-to-understand study notes
- Uses **Google Gemini GenAI**
- Built using **Java + Maven**
- Console-based and beginner friendly

---

## 🛠️ Technologies Used

- Java 17
- Maven
- Google Gemini API
- Gson (JSON parser)

---

## 📂 Project Structure

ai-study-notes-java
│
├── src/main/java/com/rakshitha/App.java
├── pom.xml
└── README.md

---

## ▶️ How to Run This Project

```bash
1️⃣ Clone the Repository
git clone https://github.com/rakshithavshetty17/ai-study-notes-java.git
cd ai-study-notes-java

2️⃣ Add Your Gemini API Key
Open App.java and replace:
private static final String API_KEY = "PUT_YOUR_API_KEY_HERE";
with your real API key.

3️⃣ Run the Application
mvn clean install
mvn exec:java

OR simply run App.java from Eclipse.

🧪 Example Output

=== AI Study Notes Generator (Java Console) ===
Enter a topic: Jenkins

✅ You will get:

Summary
Key points
Interview Q&A
