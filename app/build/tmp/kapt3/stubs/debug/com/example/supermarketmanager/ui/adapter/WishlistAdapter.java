package com.example.supermarketmanager.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B;\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\b2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/WishlistAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/supermarketmanager/ui/adapter/WishlistAdapter$VH;", "items", "", "Lcom/example/supermarketmanager/data/models/WishlistProductItem;", "onRemove", "Lkotlin/Function1;", "", "onProductClick", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "VH", "app_debug"})
public final class WishlistAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.supermarketmanager.ui.adapter.WishlistAdapter.VH> {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.supermarketmanager.data.models.WishlistProductItem> items = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.data.models.WishlistProductItem, kotlin.Unit> onRemove = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.data.models.WishlistProductItem, kotlin.Unit> onProductClick = null;
    
    public WishlistAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.models.WishlistProductItem> items, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.data.models.WishlistProductItem, kotlin.Unit> onRemove, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.data.models.WishlistProductItem, kotlin.Unit> onProductClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.supermarketmanager.ui.adapter.WishlistAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.ui.adapter.WishlistAdapter.VH holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/WishlistAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/supermarketmanager/databinding/ItemWishlistItemBinding;", "(Lcom/example/supermarketmanager/ui/adapter/WishlistAdapter;Lcom/example/supermarketmanager/databinding/ItemWishlistItemBinding;)V", "getBinding", "()Lcom/example/supermarketmanager/databinding/ItemWishlistItemBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.supermarketmanager.databinding.ItemWishlistItemBinding binding = null;
        
        public VH(@org.jetbrains.annotations.NotNull
        com.example.supermarketmanager.databinding.ItemWishlistItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.supermarketmanager.databinding.ItemWishlistItemBinding getBinding() {
            return null;
        }
    }
}