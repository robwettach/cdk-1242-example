package com.robwettach.cdk1242example;

import software.amazon.awscdk.App;

public class CDK1242ExampleApp {
    public static void main(final String argv[]) {
        App app = new App();

        new CDK1242ExampleStack(app);

        app.run();
    }
}
