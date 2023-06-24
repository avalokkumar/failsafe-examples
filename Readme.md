# Failsafe Examples

Welcome to the Failsafe Examples repository! This project aims to showcase the key features and aspects of the Failsafe library, a lightweight, flexible, and reliable Java library for handling failures and retries in applications.

## Key Features

### Retry Strategies
Failsafe offers various retry strategies, including fixed delays, exponential backoff, and custom strategies. These strategies allow you to define how retries should be performed in case of failures. You can explore different retry strategies in the provided examples to understand their behavior and choose the most suitable one for your use case.

### Circuit Breaker Pattern
Failsafe implements the circuit breaker pattern, which helps in preventing repeated failures by temporarily "opening" the circuit when a failure threshold is reached. This pattern improves resilience and reduces the impact of continuous failures. The examples in this repository demonstrate how to configure and utilize the circuit breaker pattern with Failsafe.

### Failure Handling
Failsafe provides comprehensive ways to handle different types of failures, including exceptions, timeouts, and execution failures. You can define custom handlers for specific failure scenarios, allowing you to take appropriate actions or fallback behaviors. The examples showcase different failure handling techniques, empowering you to handle failures effectively in your applications.

### Async Support
Failsafe seamlessly integrates with popular asynchronous frameworks in Java, such as CompletableFuture and RxJava. It supports asynchronous execution and allows you to handle failures in asynchronous operations effortlessly. The examples in this repository illustrate the usage of Failsafe with async APIs, enabling you to build robust and resilient asynchronous workflows.

### Execution Policies
Failsafe allows you to define execution policies that specify the conditions under which a retry or failure should occur. This provides fine-grained control over how failures are handled based on specific conditions. Explore the examples to understand how to configure and utilize execution policies effectively for your use cases.

### Integration with Java Functional APIs
Failsafe integrates well with Java's functional APIs, making it convenient to use in modern Java applications. You can leverage the power of lambdas and functional programming paradigms to express failure handling logic concisely and elegantly. The examples demonstrate how to leverage Failsafe with Java functional APIs, enhancing the readability and maintainability of your code.

## Getting Started

To explore the Failsafe Examples, follow these steps:

1. Clone this repository to your local development environment:
```shell
https://github.com/avalokkumar/failsafe-examples.git
```
2. Navigate to the specific example you're interested in:

3. Read the README file in each example directory for detailed instructions and explanations.

4. Set up the necessary dependencies and build system configuration based on the instructions provided in each example's README.

5. Run the example code and observe the behavior of Failsafe in handling failures and retries.

6. Explore other examples to gain a comprehensive understanding of Failsafe's capabilities.

## Contributing
- Contributions to this project are welcome! If you have any ideas, bug fixes, or improvements, please open an issue or submit a pull request. Let's collaborate to enhance the Failsafe Examples repository and make it a valuable resource for the community.

## Resources
- Failsafe GitHub Repository: https://github.com/failsafe-lib/failsafe
