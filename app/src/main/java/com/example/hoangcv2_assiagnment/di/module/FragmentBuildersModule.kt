package com.example.hoangcv2_assiagnment.di.module

import com.example.hoangcv2_assiagnment.ui.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProductFragment(): ProductFragment

    @ContributesAndroidInjector
    abstract fun contributeTitleFragment(): TitleFragment

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment
}