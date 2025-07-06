package com.example.supermarketmanager.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0010J$\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00130\u0018R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/supermarketmanager/ui/viewmodel/WishlistViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/supermarketmanager/data/repository/WishlistRepository;", "(Lcom/example/supermarketmanager/data/repository/WishlistRepository;)V", "_items", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/supermarketmanager/data/models/WishlistProductItem;", "items", "Landroidx/lifecycle/LiveData;", "getItems", "()Landroidx/lifecycle/LiveData;", "isFavorite", "", "productId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadItems", "", "removeItem", "wishlistId", "toggleFavorite", "onToggle", "Lkotlin/Function1;", "app_debug"})
public final class WishlistViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.supermarketmanager.data.repository.WishlistRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.supermarketmanager.data.models.WishlistProductItem>> _items = null;
    
    public WishlistViewModel(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.data.repository.WishlistRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.models.WishlistProductItem>> getItems() {
        return null;
    }
    
    public final void loadItems() {
    }
    
    public final void removeItem(int wishlistId) {
    }
    
    public final void toggleFavorite(int productId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggle) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object isFavorite(int productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}