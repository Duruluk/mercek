/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import org.mozilla.fenix.R
import org.mozilla.fenix.ext.settings
import org.mozilla.fenix.ext.showToolbar

/**
 * Settings related to the Search Optimization feature
 */
class SearchOptimizationFragment : PreferenceFragmentCompat() {
    override fun onResume() {
        super.onResume()
        showToolbar(getString(R.string.preferences_debug_settings_search_optimization))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.search_optimization_preferences, rootKey)

        requirePreference<SwitchPreference>(R.string.pref_key_search_optimization_feature).apply {
            isChecked = context.settings().isSearchOptimizationEnabled
            onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                (newValue as? Boolean)?.let { newOption ->
                    context.settings().isSearchOptimizationEnabled = newOption
                    requirePreference<SwitchPreference>(R.string.pref_key_search_optimization_stocks).apply {
                        isEnabled = newOption
                        summary = when (newOption) {
                            true -> null
                            false -> getString(R.string.preferences_debug_settings_search_optimization_stock_summary)
                        }
                        if (!newOption && isChecked) {
                            isChecked = false
                            context.settings().shouldShowSearchOptimizationStockCard = false
                        }
                    }
                }
                true
            }
        }

        requirePreference<SwitchPreference>(R.string.pref_key_search_optimization_stocks).apply {
            isEnabled = context.settings().isSearchOptimizationEnabled
            isChecked = context.settings().shouldShowSearchOptimizationStockCard
            summary = when (context.settings().isSearchOptimizationEnabled) {
                true -> null
                false -> getString(R.string.preferences_debug_settings_search_optimization_stock_summary)
            }
            onPreferenceChangeListener = SharedPreferenceUpdater()
        }
    }
}
