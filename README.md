# Latch Prediction Sand Box

This repository creates an emulated environment that multiple threads contend a lock for entering a critical section.

We can get lock's features and train a model to predict how long a thread would wait for acquiring the lock.

## Detail
The system can be seen as a M/M/1 model in queuing theory. However, the serving strategy follows ***Linux Thread Scheduling (Completely Fair Scheduler)*** instead of First-Come-First-Serve.

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
- Serving rate
  - Define how many tasks will be served in a second.

The ratio of arrival rate and serving rate determines rho.
The stationary waiters in the queue can be defined as
```
rho^2 / (1 - rho)
```

| Parameter sets | Set1 | Set2 |
| ------------- |:-------------:|:-------------:|
| Arrival Rate | 4500 | 4900 |
| Serving Rate | 5000 | 5000 |
| rho | 0.9 | 0.98 |
| Expected number of waiters in the queue | 8 | 48 |
| Expected waiting time in the queue | 1800us | 9800 us |
| Total tasks | 100_000 | 100_000 |
| Average lock waiting time | 3000 us | 3000 us |

***I recommend using at least 5000 for serving rate. Otherwise, the lock waiting time will be too long. (might be larger than 100 ms)***


