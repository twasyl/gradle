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

package org.gradle.internal.watch.registry;

import org.gradle.internal.snapshot.CompleteFileSystemLocationSnapshot;
import org.gradle.internal.snapshot.SnapshotHierarchy;
import org.gradle.internal.watch.WatchingNotSupportedException;

import java.io.File;
import java.util.Collection;

public interface FileWatcherUpdater extends SnapshotHierarchy.SnapshotDiffListener {
    /**
     * Changes the must watch directories, e.g. when the same daemon is used on a different project.
     *
     * @throws WatchingNotSupportedException when the native watchers can't be updated.
     */
    void updateMustWatchDirectories(Collection<File> updatedWatchDirectories);

    /**
     * {@inheritDoc}.
     *
     * @throws WatchingNotSupportedException when the native watchers can't be updated.
     */
    @Override
    void changed(Collection<CompleteFileSystemLocationSnapshot> removedSnapshots, Collection<CompleteFileSystemLocationSnapshot> addedSnapshots);
}
