# 🚀 Spring Boot AWS CI/CD Demo

A production-ready Spring Boot application with Docker, GitHub Actions CI/CD pipeline, AWS deployment, and Prometheus + Grafana monitoring.

## 📦 API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/hello` | GET | Returns greeting message |
| `/health` | GET | Application health status |
| `/time` | GET | Current server time |
| `/actuator/prometheus` | GET | Prometheus metrics |
| `/actuator/health` | GET | Spring Boot health check |

---

## 🏃 Quick Start (Local)

### Prerequisites
- Java 17+
- Maven 3.9+

### Run
```bash
mvn spring-boot:run
```
Visit: [http://localhost:8080/hello](http://localhost:8080/hello)

---

## 🐳 Docker

### Build & Run
```bash
docker build -t springboot-demo .
docker run -p 8080:8080 springboot-demo
```

### With Monitoring Stack (Prometheus + Grafana)
```bash
docker-compose -f docker-compose.monitoring.yml up --build
```

| Service | URL |
|---------|-----|
| App | http://localhost:8080 |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3000 (admin/admin123) |

---

## 🔄 CI/CD Pipeline (GitHub Actions)

### Pipeline Flow
```
Push to main → Build JAR → Run Tests → Build Docker Image → Push to ECR → Deploy to EC2
```

### Required GitHub Secrets

| Secret | Description |
|--------|-------------|
| `AWS_ACCESS_KEY_ID` | AWS IAM access key |
| `AWS_SECRET_ACCESS_KEY` | AWS IAM secret key |
| `EC2_HOST` | EC2 public IP / hostname |
| `EC2_USERNAME` | SSH username (e.g., `ubuntu`) |
| `EC2_SSH_KEY` | EC2 SSH private key |

---

## ☁️ AWS Setup

### 1. Create ECR Repository
```bash
aws ecr create-repository --repository-name springboot-demo --region ap-south-1
```

### 2. Launch EC2 Instance
- **AMI:** Ubuntu 22.04
- **Type:** t2.micro
- **Security Group:** Open ports 8080, 22

### 3. Install Docker on EC2
```bash
sudo apt update
sudo apt install -y docker.io awscli
sudo systemctl start docker
sudo usermod -aG docker $USER
```

---

## 📊 Monitoring

Prometheus scrapes metrics from `/actuator/prometheus`. Import the **Spring Boot Dashboard** (ID: `11378`) in Grafana for pre-built visualizations.

---

## 📁 Project Structure

```
springboot-aws-cicd-demo/
├── src/main/java/com/demo/
│   ├── DemoApplication.java
│   └── controller/
│       └── HelloController.java
├── src/main/resources/
│   └── application.yml
├── monitoring/
│   └── prometheus.yml
├── .github/workflows/
│   └── cicd.yml
├── Dockerfile
├── .dockerignore
├── docker-compose.monitoring.yml
├── pom.xml
└── README.md
```

## 🛠 Tech Stack

| Tool | Purpose |
|------|---------|
| Java 17 + Spring Boot 3.2 | Backend |
| Docker | Containerization |
| GitHub Actions | CI/CD |
| AWS ECR | Docker Image Registry |
| AWS EC2 | Deployment |
| Prometheus + Grafana | Monitoring |
