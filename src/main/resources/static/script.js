// API Base URL
const API_BASE_URL = '/api/guest';

// Tab management
function showTab(tabName) {
    // Hide all tab contents
    const tabContents = document.querySelectorAll('.tab-content');
    tabContents.forEach(content => content.classList.remove('active'));
    
    // Remove active class from all tab buttons
    const tabButtons = document.querySelectorAll('.tab-btn');
    tabButtons.forEach(btn => btn.classList.remove('active'));
    
    // Show selected tab content
    document.getElementById(tabName).classList.add('active');
    
    // Add active class to clicked button
    event.target.classList.add('active');
    
    // Load data for specific tabs
    if (tabName === 'list') {
        loadGuests('all');
    }
}

// Modal management
function showModal(message, type = 'info') {
    const modal = document.getElementById('modal');
    const modalMessage = document.getElementById('modalMessage');
    
    modalMessage.innerHTML = `<div class="message ${type}">${message}</div>`;
    modal.style.display = 'block';
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

// Close modal when clicking on X or outside
document.querySelector('.close').onclick = closeModal;
window.onclick = function(event) {
    const modal = document.getElementById('modal');
    if (event.target === modal) {
        closeModal();
    }
}

// API Helper functions
async function apiCall(url, options = {}) {
    try {
        const response = await fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        });
        
        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
        }
        
        return await response.json();
    } catch (error) {
        console.error('API Error:', error);
        throw error;
    }
}

// Create Guest Form
document.getElementById('createForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    const guestData = {
        fullName: formData.get('fullName'),
        phoneNumber: formData.get('phoneNumber'),
        email: formData.get('email') || null,
        address: formData.get('address'),
        guestType: formData.get('guestType')
    };
    
    try {
        const result = await apiCall(API_BASE_URL, {
            method: 'POST',
            body: JSON.stringify(guestData)
        });
        
        showModal('Tạo khách hàng thành công!', 'success');
        this.reset();
        
        // Auto switch to list tab to see the new guest
        setTimeout(() => {
            showTab('list');
        }, 1500);
        
    } catch (error) {
        showModal(`Lỗi: ${error.message}`, 'error');
    }
});

// Search Form
document.getElementById('searchForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    const searchParams = new URLSearchParams();
    
    // Add non-empty search parameters
    ['fullName', 'phoneNumber', 'email', 'address'].forEach(field => {
        const value = formData.get(field);
        if (value && value.trim()) {
            searchParams.append(field, value.trim());
        }
    });
    
    try {
        const results = await apiCall(`${API_BASE_URL}/search?${searchParams.toString()}`);
        displaySearchResults(results);
    } catch (error) {
        showModal(`Lỗi tìm kiếm: ${error.message}`, 'error');
    }
});

// Order Search Form
document.getElementById('orderSearchForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const phone = new FormData(this).get('phone');
    
    try {
        const result = await apiCall(`${API_BASE_URL}/orders?phone=${phone}`);
        displayOrderResults(result);
    } catch (error) {
        showModal(`Lỗi tra cứu đơn hàng: ${error.message}`, 'error');
    }
});

// Load Guests
async function loadGuests(type = 'all') {
    const resultsDiv = document.getElementById('guestList');
    resultsDiv.innerHTML = '<div class="loading">Đang tải...</div>';
    
    try {
        let guests;
        if (type === 'all') {
            guests = await apiCall(API_BASE_URL);
        } else {
            guests = await apiCall(`${API_BASE_URL}/type/${type}`);
        }
        
        displayGuestList(guests);
    } catch (error) {
        resultsDiv.innerHTML = `<div class="message error">Lỗi: ${error.message}</div>`;
    }
}

// Display Functions
function displaySearchResults(guests) {
    const resultsDiv = document.getElementById('searchResults');
    
    if (guests.length === 0) {
        resultsDiv.innerHTML = '<div class="message info">Không tìm thấy khách hàng nào.</div>';
        return;
    }
    
    resultsDiv.innerHTML = guests.map(guest => createGuestCard(guest)).join('');
}

function displayGuestList(guests) {
    const resultsDiv = document.getElementById('guestList');
    
    if (guests.length === 0) {
        resultsDiv.innerHTML = '<div class="message info">Chưa có khách hàng nào.</div>';
        return;
    }
    
    resultsDiv.innerHTML = guests.map(guest => createGuestCard(guest)).join('');
}

function displayOrderResults(result) {
    const resultsDiv = document.getElementById('orderResults');
    
    if (!result.guest) {
        resultsDiv.innerHTML = '<div class="message error">Không tìm thấy khách hàng với số điện thoại này.</div>';
        return;
    }
    
    const guest = result.guest;
    const orders = result.orders || [];
    
    let html = `
        <div class="guest-card">
            <div class="guest-header">
                <div class="guest-name">${guest.fullName}</div>
                <span class="guest-type ${guest.guestType.toLowerCase()}">${guest.guestType === 'SENDER' ? 'Người gửi' : 'Người nhận'}</span>
            </div>
            <div class="guest-info">
                <div class="guest-info-item">
                    <i>📞</i> ${guest.phoneNumber}
                </div>
                ${guest.email ? `<div class="guest-info-item"><i>📧</i> ${guest.email}</div>` : ''}
                <div class="guest-info-item">
                    <i>📍</i> ${guest.address}
                </div>
            </div>
        </div>
    `;
    
    if (orders.length === 0) {
        html += '<div class="message info">Chưa có đơn hàng nào cho khách hàng này.</div>';
    } else {
        html += '<h3>Danh sách đơn hàng:</h3>';
        html += orders.map(order => `
            <div class="guest-card">
                <div class="guest-header">
                    <div class="guest-name">Đơn hàng #${order.id}</div>
                    <span class="guest-type">${order.status}</span>
                </div>
                <div class="guest-info">
                    <div class="guest-info-item">
                        <i>📅</i> Ngày tạo: ${new Date(order.createdAt).toLocaleDateString('vi-VN')}
                    </div>
                    <div class="guest-info-item">
                        <i>💰</i> Tổng tiền: ${order.totalAmount?.toLocaleString('vi-VN')} VNĐ
                    </div>
                </div>
            </div>
        `).join('');
    }
    
    resultsDiv.innerHTML = html;
}

function createGuestCard(guest) {
    return `
        <div class="guest-card">
            <div class="guest-header">
                <div class="guest-name">${guest.fullName}</div>
                <span class="guest-type ${guest.guestType.toLowerCase()}">${guest.guestType === 'SENDER' ? 'Người gửi' : 'Người nhận'}</span>
            </div>
            <div class="guest-info">
                <div class="guest-info-item">
                    <i>📞</i> ${guest.phoneNumber}
                </div>
                ${guest.email ? `<div class="guest-info-item"><i>📧</i> ${guest.email}</div>` : ''}
                <div class="guest-info-item">
                    <i>📍</i> ${guest.address}
                </div>
                <div class="guest-info-item">
                    <i>📅</i> Tạo: ${new Date(guest.createdAt).toLocaleDateString('vi-VN')}
                </div>
            </div>
            <div class="guest-actions">
                <button class="btn btn-secondary" onclick="editGuest(${guest.id})">✏️ Sửa</button>
                <button class="btn btn-danger" onclick="deleteGuest(${guest.id})">🗑️ Xóa</button>
                <button class="btn btn-success" onclick="viewOrders('${guest.phoneNumber}')">📦 Xem đơn hàng</button>
            </div>
        </div>
    `;
}

// Action Functions
async function editGuest(id) {
    try {
        const guest = await apiCall(`${API_BASE_URL}/${id}`);
        
        // Populate form with guest data
        document.getElementById('fullName').value = guest.fullName;
        document.getElementById('phoneNumber').value = guest.phoneNumber;
        document.getElementById('email').value = guest.email || '';
        document.getElementById('address').value = guest.address;
        document.getElementById('guestType').value = guest.guestType;
        
        // Switch to create tab and change button text
        showTab('create');
        const submitBtn = document.querySelector('#createForm button[type="submit"]');
        submitBtn.textContent = 'Cập nhật khách hàng';
        
        // Change form action to update
        const form = document.getElementById('createForm');
        form.dataset.editId = id;
        form.onsubmit = updateGuestHandler;
        
    } catch (error) {
        showModal(`Lỗi: ${error.message}`, 'error');
    }
}

async function updateGuestHandler(e) {
    e.preventDefault();
    
    const form = e.target;
    const id = form.dataset.editId;
    
    const formData = new FormData(form);
    const guestData = {
        fullName: formData.get('fullName'),
        phoneNumber: formData.get('phoneNumber'),
        email: formData.get('email') || null,
        address: formData.get('address'),
        guestType: formData.get('guestType')
    };
    
    try {
        await apiCall(`${API_BASE_URL}/${id}`, {
            method: 'PUT',
            body: JSON.stringify(guestData)
        });
        
        showModal('Cập nhật khách hàng thành công!', 'success');
        
        // Reset form
        form.reset();
        form.dataset.editId = '';
        form.onsubmit = null;
        
        // Reset button text
        const submitBtn = form.querySelector('button[type="submit"]');
        submitBtn.textContent = 'Tạo khách hàng';
        
        // Reload guest list
        setTimeout(() => {
            showTab('list');
        }, 1500);
        
    } catch (error) {
        showModal(`Lỗi: ${error.message}`, 'error');
    }
}

async function deleteGuest(id) {
    if (!confirm('Bạn có chắc chắn muốn xóa khách hàng này?')) {
        return;
    }
    
    try {
        await apiCall(`${API_BASE_URL}/${id}`, {
            method: 'DELETE'
        });
        
        showModal('Xóa khách hàng thành công!', 'success');
        loadGuests('all');
        
    } catch (error) {
        showModal(`Lỗi: ${error.message}`, 'error');
    }
}

function viewOrders(phoneNumber) {
    document.getElementById('orderPhone').value = phoneNumber;
    showTab('orders');
    document.getElementById('orderSearchForm').dispatchEvent(new Event('submit'));
}

// Initialize page
document.addEventListener('DOMContentLoaded', function() {
    // Load initial data
    loadGuests('all');
    
    // Add phone number validation
    const phoneInputs = document.querySelectorAll('input[type="tel"]');
    phoneInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            // Remove non-numeric characters except + and -
            this.value = this.value.replace(/[^\d+\-]/g, '');
        });
    });
}); 