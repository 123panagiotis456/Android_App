package com.example.supermarketmanager.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001bB;\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\bH\u0016J\u001c\u0010\u0011\u001a\u00020\f2\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0016J(\u0010\u0018\u001a\u00020\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/PurchaseHistoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/supermarketmanager/ui/adapter/PurchaseHistoryAdapter$PurchaseViewHolder;", "purchases", "", "Lcom/example/supermarketmanager/data/entities/PurchaseHistoryEntity;", "productIdToName", "", "", "", "onReaddClick", "Lkotlin/Function1;", "", "(Ljava/util/List;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newPurchases", "newProductIdToName", "PurchaseViewHolder", "app_debug"})
public final class PurchaseHistoryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter.PurchaseViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.supermarketmanager.data.entities.PurchaseHistoryEntity> purchases;
    @org.jetbrains.annotations.NotNull
    private java.util.Map<java.lang.Integer, java.lang.String> productIdToName;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.data.entities.PurchaseHistoryEntity, kotlin.Unit> onReaddClick = null;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public PurchaseHistoryAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.entities.PurchaseHistoryEntity> purchases, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.Integer, java.lang.String> productIdToName, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.data.entities.PurchaseHistoryEntity, kotlin.Unit> onReaddClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter.PurchaseViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.ui.adapter.PurchaseHistoryAdapter.PurchaseViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.entities.PurchaseHistoryEntity> newPurchases, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.Integer, java.lang.String> newProductIdToName) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/PurchaseHistoryAdapter$PurchaseViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/supermarketmanager/databinding/ItemPurchaseHistoryBinding;", "(Lcom/example/supermarketmanager/ui/adapter/PurchaseHistoryAdapter;Lcom/example/supermarketmanager/databinding/ItemPurchaseHistoryBinding;)V", "getBinding", "()Lcom/example/supermarketmanager/databinding/ItemPurchaseHistoryBinding;", "app_debug"})
    public final class PurchaseViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding binding = null;
        
        public PurchaseViewHolder(@org.jetbrains.annotations.NotNull
        com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.supermarketmanager.databinding.ItemPurchaseHistoryBinding getBinding() {
            return null;
        }
    }
}