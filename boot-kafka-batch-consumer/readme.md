1. Enable the Batch Listener property of `ConcurrentKafkaListenerContainerFactory` using the method `setBatchListener(true)`.
2. Create a BatchErrorHandler using the method `setBatchErrorHandler(new BatchLoggingErrorHandler())`. You may create your own Batch Error Handler.
3. Set an upper limit for the batch size by setting the `ConsumerConfig.MAX_POLL_RECORDS_CONFIG` to a value that you need. Here we are setting that value to 4.

