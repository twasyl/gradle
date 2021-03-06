/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.provider

import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class NestedBeanJavaInterOpIntegrationTest extends AbstractNestedBeanJavaInterOpIntegrationTest {
    def setup() {
        pluginDir.file("src/main/java/SomeTask.java") << """
            import ${DefaultTask.name};
            import ${ObjectFactory.name};
            import ${TaskAction.name};
            import ${Nested.name};
            import ${Inject.name};

            public class SomeTask extends DefaultTask {
                private final Params params;

                @Nested
                public Params getParams() {
                    return params;
                }

                @Inject
                public SomeTask(ObjectFactory objectFactory) {
                    params = objectFactory.newInstance(Params.class);
                }

                @TaskAction
                public void run() {
                    System.out.println("flag = " + getParams().getFlag().get());
                }
            }
        """
    }
}
