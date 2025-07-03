package com.example.supermarketmanager.ui.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006 "}, d2 = {"Lcom/example/supermarketmanager/ui/fragment/ProductDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/supermarketmanager/databinding/FragmentProductDetailBinding;", "args", "Lcom/example/supermarketmanager/ui/fragment/ProductDetailFragmentArgs;", "getArgs", "()Lcom/example/supermarketmanager/ui/fragment/ProductDetailFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/example/supermarketmanager/databinding/FragmentProductDetailBinding;", "isFavorite", "", "quantity", "", "vm", "Lcom/example/supermarketmanager/ui/viewmodel/ProductViewModel;", "getVm", "()Lcom/example/supermarketmanager/ui/viewmodel/ProductViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onDestroyView", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "updateFavoriteIcon", "app_debug"})
public final class ProductDetailFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.supermarketmanager.databinding.FragmentProductDetailBinding _binding;
    @org.jetbrains.annotations.NotNull
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy vm$delegate = null;
    private int quantity = 1;
    private boolean isFavorite = false;
    
    public ProductDetailFragment() {
        super();
    }
    
    private final com.example.supermarketmanager.databinding.FragmentProductDetailBinding getBinding() {
        return null;
    }
    
    private final com.example.supermarketmanager.ui.fragment.ProductDetailFragmentArgs getArgs() {
        return null;
    }
    
    private final com.example.supermarketmanager.ui.viewmodel.ProductViewModel getVm() {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateFavoriteIcon() {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}