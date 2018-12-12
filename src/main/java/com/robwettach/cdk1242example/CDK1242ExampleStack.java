package com.robwettach.cdk1242example;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.cloudformation.SubscriptionResource;
import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.sqs.QueueProps;

public class CDK1242ExampleStack extends Stack {
    public CDK1242ExampleStack(App parent) {
        super(parent, "CDKExample");

        Queue queue = new Queue(this, "Queue", QueueProps.builder().build());

        Topic topic = new Topic(this, "Topic");

        topic.subscribeQueue(queue);
        SubscriptionResource subscription = (SubscriptionResource) topic.findChild(queue.getId() + "Subscription")
                .findChild("Resource");
        // Fails with the following exception:
        // Exception in thread "main" software.amazon.jsii.JsiiException: While synthesizing CDKExample/Topic/QueueSubscription/Resource: Maximum call stack size exceeded
        // While synthesizing CDKExample/Topic/QueueSubscription/Resource: Maximum call stack size exceeded
        // --- resource created at ---
        // at new Subscription (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-NvyY5e/node_modules/@aws-cdk/aws-sns/lib/subscription.js:14:9)
        // at Topic.subscribeQueue (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-NvyY5e/node_modules/@aws-cdk/aws-sns/lib/topic-ref.js:66:21)
        subscription.getPropertyOverrides().setRawMessageDelivery(false);

        // This works.
        // subscription.addOverride("Properties.RawMessageDelivery", true);
    }
}
