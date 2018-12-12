#!/usr/bin/env node
import cdk = require('@aws-cdk/cdk');
import { CDK1242ExampleStack } from '../lib/cdk-1242-example-stack';

const app = new cdk.App();
new CDK1242ExampleStack(app);
app.run();
