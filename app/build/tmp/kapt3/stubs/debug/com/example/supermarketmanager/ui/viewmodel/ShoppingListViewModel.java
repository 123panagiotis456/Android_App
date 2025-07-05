package com.example.supermarketmanager.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0012"}, d2 = {"Lcom/example/supermarketmanager/ui/viewmodel/ShoppingListViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_cartItems", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/supermarketmanager/data/models/ShoppingCartItem;", "cartItems", "Landroidx/lifecycle/LiveData;", "getCartItems", "()Landroidx/lifecycle/LiveData;", "loadCartItems", "", "makePurchase", "Lkotlinx/coroutines/Job;", "readdPurchaseToCart", "purchase", "Lcom/example/supermarketmanager/data/entities/PurchaseHistoryEntity;", "app_debug"})
public final class ShoppingListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem>> _cartItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem>> cartItems = null;
    
    public ShoppingListViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem>> getCartItems() {
        return null;
    }
    
    public final void loadCartItems() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job makePurchase() {
        return null;
    }
    
    /**
     * Drop all items from a past purchase back into the cart
     */
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job readdPurchaseToCart(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.data.entities.PurchaseHistoryEntity purchase) {
        return null;
    }
}