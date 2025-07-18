<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="商品货号" prop="goodCover">
        <el-input
          v-model="queryParams.goodCover"
          placeholder="请输入商品货号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodName">
        <el-input
          v-model="queryParams.goodName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品描述" prop="goodDescribe">
        <el-input
          v-model="queryParams.goodDescribe"
          placeholder="请输入商品描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-option-group> </el-option-group>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['goods:good:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['goods:good:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['goods:good:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['goods:good:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="goodList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="id" />
      <el-table-column label="商品货号" align="center" prop="goodCover" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="商品描述" align="center" prop="goodDescribe" />
      <el-table-column label="商品类别" align="center" prop="type" />
      <el-table-column label="价格" align="center" prop="goodPrice" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goods:good:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goods:good:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品货号" prop="goodCover">
          <el-input v-model="form.goodCover" placeholder="请输入商品货号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodName">
          <el-input v-model="form.goodName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品类型" prop="type">
          <el-cascader
            v-model="form.type"
            :options="typeOptions"
            :props="{ value: 'type', label: 'type', children: 'children' }"
            clearable
            placeholder="请选择商品类型"
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="商品描述" prop="goodDescribe">
          <el-input v-model="form.goodDescribe" placeholder="请输入商品描述" />
        </el-form-item>
        <el-form-item label="价格" prop="goodPrice">
          <el-input v-model="form.goodPrice" placeholder="请输入价格" />
        </el-form-item>
      </el-form>
      <el-divider>商品款式图片</el-divider>
      <el-button type="primary" icon="el-icon-plus" @click="addStyle"
        >添加款式</el-button
      >

      <div
        v-for="(style, index) in styleList"
        :key="index"
        class="style-section"
      >
        <el-row :gutter="10" class="mt10 mb10">
          <el-col :span="10">
            <el-input v-model="style.styleName" placeholder="请输入款式名称" />
          </el-col>
          <el-col :span="4">
            <el-button
              type="danger"
              icon="el-icon-delete"
              @click="removeStyle(index)"
              >删除款式</el-button
            >
          </el-col>
        </el-row>

        <el-upload
          :action="uploadUrl"
          :headers="uploadHeaders"
          list-type="picture-card"
          :file-list="style.fileList"
          :on-success="
            (res, file, fileList) =>
              handleUploadSuccess(res, file, fileList, index)
          "
          :on-remove="(file, fileList) => handleRemove(file, fileList, index)"
          :before-upload="() => beforeUpload(index)"
          multiple
        >
          <i class="el-icon-plus"></i>
        </el-upload>

        <div v-if="style.fileList.length > 0" class="main-image-radio">
          <span>选择主图：</span>
          <el-radio-group v-model="style.mainImage">
            <el-radio
              v-for="file in style.fileList"
              :key="file.uid"
              :label="file.url || (file.response && file.response.url)"
            >
              <img
                :src="file.url || (file.response && file.response.url)"
                class="thumb"
                style="width: 40px; height: 40px"
              />
            </el-radio>
          </el-radio-group>
        </div>

        <el-divider></el-divider>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGood,
  getGood,
  delGood,
  addGood,
  updateGood,
} from "@/api/goods/good";
import { getTypeTree } from "@/api/goods/type";
import { addStyle, listStyle, replaceStyle } from "@/api/goods/style";
import { getToken } from "@/utils/auth";
import request from "@/utils/request";

export default {
  name: "Good",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品信息表格数据
      goodList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodCover: null,
        goodName: null,
        goodDescribe: null,
        type: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      typeOptions: [],
      styleList: [], // 每个商品的款式集合
      uploadUrl: process.env.VUE_APP_BASE_API + "/goods/style/upload",
      uploadHeaders: {
        Authorization: "Bearer " + getToken(),
      },
    };
  },
  created() {
    this.getTypeTree();
    this.getList();
    this.styleList = [];
  },
  methods: {
    getTypeTree() {
      getTypeTree().then((res) => {
        this.typeOptions = res.data;
      });
    },
    /** 查询商品信息列表 */
    getList() {
      // 查询前处理类型参数
      if (Array.isArray(this.queryParams.type)) {
        this.queryParams.type =
          this.queryParams.type[this.queryParams.type.length - 1];
      }
      this.loading = true;
      listGood(this.queryParams).then((response) => {
        this.goodList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    findTypePath(value, options = this.typeOptions, path = []) {
      for (const item of options) {
        const currentPath = [...path, item.type];
        if (item.type === value) {
          return currentPath;
        }
        if (item.children) {
          const found = this.findTypePath(value, item.children, currentPath);
          if (found.length) return found;
        }
      }
      return [];
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        goodCover: null,
        goodName: null,
        goodDescribe: null,
        type: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        goodPrice: null,
      };
      this.styleList = [];
      this.resetForm("form");
    },

    // 添加一个款式
    addStyle() {
      this.styleList.push({
        styleName: "",
        fileList: [],
        mainImage: "",
      });
    },

    // 删除一个款式
    removeStyle(index) {
      this.styleList.splice(index, 1);
    },

    // 上传前校验
    beforeUpload(index) {
      if (!this.styleList[index].styleName) {
        this.$message.warning("请先填写款式名称");
        return false;
      }
      return true;
    },

    // 上传成功
    handleUploadSuccess(res, file, fileList, index) {
      // 兼容 AjaxResult 包裹结构
      const url = res.url || (res.data && res.data.url);
      if (!url) {
        this.$message.error("上传失败：图片地址未返回");
        return;
      }

      file.url = url;
      file.response = res.data || res;

      // 替换 fileList 并设置主图
      this.$set(this.styleList[index], "fileList", [...fileList]);

      if (!this.styleList[index].mainImage) {
        this.styleList[index].mainImage = url;
      }
    },

    // 删除图片
    handleRemove(file, fileList, index) {
      this.styleList[index].fileList = fileList;
    },

    /** 搜索按钮操作 */
    handleQuery() {
      // 查询前处理类型参数
      if (Array.isArray(this.queryParams.type)) {
        this.queryParams.type =
          this.queryParams.type[this.queryParams.type.length - 1];
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;

      getGood(id).then((response) => {
        this.form = response.data;

        // ⭐ 把字符串类型转成完整的级联路径数组
        if (this.form.type && typeof this.form.type === "string") {
          this.form.type = this.findTypePath(this.form.type);
        }

        // ✅ 获取款式图（已有逻辑）
        listStyle({ goodId: id }).then((res) => {
          const styleMap = {};

          res.rows.forEach((img) => {
            if (!styleMap[img.style]) {
              styleMap[img.style] = {
                styleName: img.style,
                fileList: [],
                mainImage: "",
              };
            }
            styleMap[img.style].fileList.push({
              name: img.image.split("/").pop(),
              url: img.image,
              status: "done",
              uid: img.id,
            });
            if (img.isMain === 1) {
              styleMap[img.style].mainImage = img.image;
            }
          });

          this.styleList = Object.values(styleMap);
        });

        this.open = true;
        this.title = "修改商品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      if (Array.isArray(this.form.type)) {
        this.form.type = this.form.type[this.form.type.length - 1];
      }

      this.$refs["form"].validate(async (valid) => {
        if (!valid) return;

        const isEdit = this.form.id != null;
        const request = isEdit ? updateGood : addGood;

        try {
          // 保存基本商品信息
          const res = await request(this.form);

          // 拿到商品 ID（修改用 form.id，新增用返回值）
          const goodId = this.form.id || res.data.id || res.data;

          // 如果是编辑，处理款式图替换
          if (isEdit) {
            const imageList = [];

            for (const style of this.styleList) {
              for (const file of style.fileList) {
                const imageUrl =
                  file.url || (file.response && file.response.url);
                if (!imageUrl || imageUrl.startsWith("blob:")) continue;

                imageList.push({
                  goodId,
                  style: style.styleName,
                  image: imageUrl,
                  isMain: imageUrl === style.mainImage ? 1 : 0,
                  sort: 0,
                });
              }
            }

            // 发起“替换接口”
            await replaceStyle(goodId, imageList);
          }

          this.$modal.msgSuccess(isEdit ? "修改成功" : "新增成功");
          this.open = false;
          this.getList();
        } catch (err) {
          console.error("保存失败", err);
          this.$modal.msgError("保存失败");
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除商品信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delGood(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "goods/good/export",
        {
          ...this.queryParams,
        },
        `good_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
