## Example for error found in [awscdk-1242](https://github.com/awslabs/aws-cdk/issues/1242)

Thanks Elad for showing me how to [access the CloudFormation layer](https://awslabs.github.io/aws-cdk/aws-construct-lib.html#access-the-aws-cloudformation-layer) for taking advantage of CloudFormation properties that aren't exposed via CDK.  While attempting to [Override Resource Properties](https://awslabs.github.io/aws-cdk/aws-construct-lib.html#overriding-resource-properties) using the `getPropertyOverrides()` method I ran into a "Maximum stack size exceeded" error.  Using the `addOverride("Properties.RawMessageDelivery")` method succeeds.

Here's the stack trace.  You can get this as well by just calling `mvn package && cdk synth`.

```
Exception in thread "main" software.amazon.jsii.JsiiException: While synthesizing CDKExample/Topic/QueueSubscription/Resource: Maximum call stack size exceeded
While synthesizing CDKExample/Topic/QueueSubscription/Resource: Maximum call stack size exceeded
  --- resource created at ---
  at new Subscription (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/aws-sns/lib/subscription.js:14:9)
  at Topic.subscribeQueue (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/aws-sns/lib/topic-ref.js:66:21)
  at _wrapSandboxCode (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:84007)
  at t.Kernel._wrapSandboxCode (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:95151)
  at a._ensureSync (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:83984)
  at t.Kernel._ensureSync (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:94687)
  at t.Kernel.invoke (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:83929)
  at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78720)
  at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
  at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
  at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
  at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
  at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
  at i.then.e (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79048)
  --- problem discovered at ---
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:197:19)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at deepMerge (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-kernel-YlzZ7B/node_modules/@aws-cdk/cdk/lib/cloudformation/resource.js:209:13)
    at t.Kernel._wrapSandboxCode (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:95170)
    at a._ensureSync (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:83984)
    at t.Kernel._ensureSync (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:94687)
    at t.Kernel.invoke (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:83929)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78720)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
    at e.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78435)
    at t.KernelHost.processRequest (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:79174)
    at t.KernelHost.run (/private/var/folders/0y/l3zzxxh96w51j_hmrc852_y83tcgzz/T/jsii-java-runtime923197212800794521/jsii-runtime.js:1:78409)
        at software.amazon.jsii.JsiiRuntime.processErrorResponse(JsiiRuntime.java:118)
        at software.amazon.jsii.JsiiRuntime.requestResponse(JsiiRuntime.java:90)
        at software.amazon.jsii.JsiiClient.callMethod(JsiiClient.java:196)
        at software.amazon.jsii.JsiiObject.jsiiCall(JsiiObject.java:61)
        at software.amazon.awscdk.App.run(App.java:31)
        at com.robwettach.cdk1242example.CDK1242ExampleApp.main(CDK1242ExampleApp.java:11)
```

---
Welcome to your CDK Java project!

It is a Maven-based project, so you can open this directory with any Maven-compatible Java IDE,
and you should be able to build and run tests from your IDE.

You should explore the contents of this template. It demonstrates a CDK app with two instances of
a stack (`HelloStack`) which also uses a user-defined construct (`HelloConstruct`).

The `cdk.json` file tells the CDK Toolkit how to execute your app. It uses a script called `app.sh`
to do that. Note that this script expects a local file called `.classpath.txt` to exist. This file
is automatically created by `mvn package`.

# Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!

