import cdk = require('@aws-cdk/cdk');
import sns = require('@aws-cdk/aws-sns');
import sqs = require('@aws-cdk/aws-sqs');

export class CDK1242ExampleStack extends cdk.Stack {
  constructor(parent: cdk.App) {
    super(parent, "CDKExample");

    const queue = new sqs.Queue(this, "Queue");

    const topic = new sns.Topic(this, "Topic");

    topic.subscribeQueue(queue);
    const subscription = topic.findChild(queue.id + "Subscription")
            .findChild("Resource") as sns.cloudformation.SubscriptionResource;
    
    // This didn't work in Java, but it works in TypeScript
    subscription.propertyOverrides.rawMessageDelivery = true;

    // This works too
    // subscription.addOverride("Properties.RawMessageDelivery", true);
  }
}
