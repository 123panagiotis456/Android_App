package com.example.supermarketmanager.ui.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0018B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\u0010\u0006J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u001a\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/supermarketmanager/ui/fragment/SortBottomSheetDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "onApplySort", "Lkotlin/Function1;", "Lcom/example/supermarketmanager/ui/fragment/SortBottomSheetDialogFragment$SortOption;", "", "(Lkotlin/jvm/functions/Function1;)V", "_binding", "Lcom/example/supermarketmanager/databinding/BottomSheetSortBinding;", "binding", "getBinding", "()Lcom/example/supermarketmanager/databinding/BottomSheetSortBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onStart", "onViewCreated", "view", "SortOption", "app_debug"})
public final class SortBottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.ui.fragment.SortBottomSheetDialogFragment.SortOption, kotlin.Unit> onApplySort = null;
    @org.jetbrains.annotations.Nullable
    private com.example.supermarketmanager.databinding.BottomSheetSortBinding _binding;
    
    public SortBottomSheetDialogFragment(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.ui.fragment.SortBottomSheetDialogFragment.SortOption, kotlin.Unit> onApplySort) {
        super();
    }
    
    private final com.example.supermarketmanager.databinding.BottomSheetSortBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onStart() {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/supermarketmanager/ui/fragment/SortBottomSheetDialogFragment$SortOption;", "", "(Ljava/lang/String;I)V", "DEFAULT", "PRICE", "DISCOUNT", "UNIT_PRICE", "app_debug"})
    public static enum SortOption {
        /*public static final*/ DEFAULT /* = new DEFAULT() */,
        /*public static final*/ PRICE /* = new PRICE() */,
        /*public static final*/ DISCOUNT /* = new DISCOUNT() */,
        /*public static final*/ UNIT_PRICE /* = new UNIT_PRICE() */;
        
        SortOption() {
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.example.supermarketmanager.ui.fragment.SortBottomSheetDialogFragment.SortOption> getEntries() {
            return null;
        }
    }
}