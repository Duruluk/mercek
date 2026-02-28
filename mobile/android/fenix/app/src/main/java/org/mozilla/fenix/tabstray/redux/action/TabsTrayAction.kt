/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.tabstray.redux.action

import mozilla.components.browser.state.state.TabSessionState
import mozilla.components.lib.state.Action
import org.mozilla.fenix.tabstray.redux.state.Page
import org.mozilla.fenix.tabstray.redux.state.TabsTrayState
import org.mozilla.fenix.tabstray.redux.store.TabsTrayStore
import org.mozilla.fenix.tabstray.syncedtabs.SyncedTabsListItem

/**
 * [Action]s dispatched to change the state of [TabsTrayState] or otherwise perform side effects
 * via [TabsTrayStore].
 */
sealed interface TabsTrayAction : Action {

    /**
     * Entered multi-select mode.
     */
    object EnterSelectMode : TabsTrayAction

    /**
     * Exited multi-select mode.
     */
    object ExitSelectMode : TabsTrayAction

    /**
     * Added a new [mozilla.components.browser.state.state.TabSessionState] to the selection set.
     */
    data class AddSelectTab(val tab: TabSessionState) : TabsTrayAction

    /**
     * Removed a [TabSessionState] from the selection set.
     */
    data class RemoveSelectTab(val tab: TabSessionState) : TabsTrayAction

    /**
     * The active page in the tray that is now in focus.
     */
    data class PageSelected(val page: Page) : TabsTrayAction

    /**
     * A request to perform a "sync" action.
     */
    object SyncNow : TabsTrayAction

    /**
     * When a "sync" action has completed; this can be triggered immediately after [SyncNow] if
     * no sync action was able to be performed.
     */
    object SyncCompleted : TabsTrayAction

    /**
     * Updates the [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.inactiveTabsExpanded] boolean
     *
     * @property expanded The updated boolean to [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.inactiveTabsExpanded]
     */
    data class UpdateInactiveExpanded(val expanded: Boolean) : TabsTrayAction

    /**
     * Updates the list of tabs in [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.inactiveTabs].
     */
    data class UpdateInactiveTabs(val tabs: List<TabSessionState>) : TabsTrayAction

    /**
     * Updates the list of tabs in [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.normalTabs].
     */
    data class UpdateNormalTabs(val tabs: List<TabSessionState>) : TabsTrayAction

    /**
     * Updates the list of tabs in [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.privateTabs].
     */
    data class UpdatePrivateTabs(val tabs: List<TabSessionState>) : TabsTrayAction

    /**
     * Updates the list of synced tabs in [org.mozilla.fenix.tabstray.redux.state.TabsTrayState.syncedTabs].
     */
    data class UpdateSyncedTabs(val tabs: List<SyncedTabsListItem>) : TabsTrayAction

    /**
     * Updates the selected tab id.
     *
     * @property tabId The ID of the tab that is currently selected.
     */
    data class UpdateSelectedTabId(val tabId: String?) : TabsTrayAction

    /**
     * Expands or collapses the header on the synced tabs page.
     *
     * @property index The index of the header.
     */
    data class SyncedTabsHeaderToggled(val index: Int) : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the tab auto close dialog is shown.
     */
    object TabAutoCloseDialogShown : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user requests to share all of their normal tabs.
     */
    object ShareAllNormalTabs : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user requests to share all of their private tabs.
     */
    object ShareAllPrivateTabs : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user requests to close all normal tabs.
     */
    object CloseAllNormalTabs : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user requests to close all private tabs.
     */
    object CloseAllPrivateTabs : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the three-dot menu is displayed to the user.
     */
    object ThreeDotMenuShown : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user requests to bookmark selected tabs.
     */
    data class BookmarkSelectedTabs(val tabCount: Int) : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user clicks on the Tab Search icon.
     */
    object TabSearchClicked : TabsTrayAction

    /**
     * [TabsTrayAction] fired when the user clicks on the back button or swipes to navigate back.
     */
    object NavigateBackInvoked : TabsTrayAction
}
