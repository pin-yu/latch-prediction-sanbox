# Latch Prediction Sandbox

This repo creates an emulated environment that multiple threads contend a lock for entering a critical section.

We can get lock's features and train a model to predict how long a thread would wait for acquiring the lock.

## Detail
The system can be seen as a M/M/1 model in queueing theory. However, the serving strategy follows ***Linux Thread Scheduling (Completely Fair Scheduler)*** instead of First-Come-First-Serve.

### Components
- Task generator
  - Generate tasks at certain times.
  - The interval between two tasks follows ***Exponential Distribution*** with a given arrival rate.
- Task executor
  - The executor, is simply a thread pool, acts like a queue with fixed size 1000.
  - Tasks will be put into the executor.
- Task
  - Acquire a lock, do busy wait, then release lock.
  - Busy wait time also follows ***Exponential Distribution*** with a given serving rate.

### Parameters
This section explains how to adjust the parameters.

- Total tasks
  - Define how many tasks to do.
- Arrival rate
  - Define how many tasks will be generated in a second.
- Seving rate
  - Define how many tasks will be served in a second.

The ratio of arrival rate and serving rate determines rho.
The stationary waiters in the queue can be defined as *rho^2 / (1 - rho)*.

| Parameter sets | Default |
| ------------- |:-------------:|
| Arrival Rate | 4500 |
| Serving Rate | 5000 |
| rho | 0.9 |
| Staionary waiters in a queue | 8 |
| Total tasks | 100_000 |
