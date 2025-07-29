# 🚚 DHL Resource Allocator

A Spring Boot API that calculates the optimal machine allocation per region to fulfill a given capacity at minimum cost.

---

## 📌 Problem Description

Given different machine types with fixed capacities and different hourly costs depending on the region (New York, India, China), the system should determine the cheapest way to fulfill a capacity for a given number of hours.

---

## 🧱 Architecture

This project follows **Clean Architecture**:

```
src/
├── domain/          → Entities: MachineType, Region, AllocationResult
├── application/     → Business logic: CostPlannerService
├── infrastructure/  → DataProvider: simulates machine cost and availability
├── interfaces/      → REST API: AllocationController + DTOs
└── test/            → Unit tests with JUnit
```

---

## 🧠 Allocation Logic

1. All available machine types are sorted by **cost efficiency** (`cost / capacity`)
2. The algorithm uses the most cost-effective machines first (greedy)
3. It returns:

   * The total cost
   * The machine combination used
   * Per region result

---

## ⚙️ Access Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

## 🚀 API Endpoint

**POST** `/api/allocate`

### Example Input:

```json
{
  "capacity": 1150,
  "hours": 1
}
```

### Example Output:

```json
{
  "output": [
    {
      "region": "New York",
      "totalCost": 10150,
      "machines": {
        "8XLarge": 7,
        "XLarge": 1,
        "Large": 1
      }
    },
    {
      "region": "India",
      "totalCost": 9520,
      "machines": {
        "8XLarge": 7,
        "Large": 3
      }
    },
    {
      "region": "China",
      "totalCost": 8570,
      "machines": {
        "8XLarge": 7,
        "XLarge": 1,
        "Large": 1
      }
    }
  ]
}
```

---

## 🧪 Sample Test Case

**Input:**

```json
{
  "capacity": 50,
  "hours": 1
}
```

**Expected Output (China):**

```json
{
  "region": "China",
  "totalCost": 510,
  "machines": {
    "XLarge": 2,
    "Large": 1
  }
}
```

---

## 📌 Technologies

* Java 17
* Spring Boot 3.5+
* Maven
* Swagger (SpringDoc)

---

## 👨‍💼 Author

**Alexander López**
Fullstack Developer
GitHub: [@Alexlop175](https://github.com/Alexlop175) OR [@Alexlop175Cenfotec](https://github.com/Alexlop175Cenfotec)

---
